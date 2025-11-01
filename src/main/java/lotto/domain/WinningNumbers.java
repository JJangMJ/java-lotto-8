package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.dto.LottoPurchaseResult;
import lotto.dto.WinningStatistic;
import lotto.validator.Validator;

public class WinningNumbers {
    private final List<Integer> numbers;

    public WinningNumbers(List<Integer> numbers) {
        Validator.validateNumberCount(numbers);
        Validator.validateNumbersRange(numbers);
        Validator.validateDuplicateNumber(numbers);
        this.numbers = numbers;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public WinningStatistic getWinningStatistic(LottoPurchaseResult lottoPurchaseResult, BonusNumber bonusNumber) {
        Map<Rank, Integer> rankResults = initializeRankResults();
        int totalPrize = 0;

        for (Lotto purchasedLotto : lottoPurchaseResult.purchasedLottos()) {
            int matchCount = purchasedLotto.countMatches(this);
            boolean hasBonusNumber = purchasedLotto.contains(bonusNumber);
            Rank rank = Rank.calculateRank(matchCount, hasBonusNumber);
            rankResults.put(rank, rankResults.get(rank) + 1);
            totalPrize += rank.getPrize();
        }
        double profitRate = Math.round(((double) totalPrize / lottoPurchaseResult.purchaseCount())) / 10.0;

        return new WinningStatistic(rankResults, profitRate);
    }

    private Map<Rank, Integer> initializeRankResults() {
        Map<Rank, Integer> rankResults = new HashMap<>();
        Arrays.stream(Rank.values())
                .forEach(rank -> rankResults.putIfAbsent(rank, 0));
        return rankResults;
    }
}
