package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lotto.dto.LottoPurchaseResult;
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

    public Map<Rank, Integer> getRankResults(LottoPurchaseResult lottoPurchaseResult, BonusNumber bonusNumber) {
        Map<Rank, Integer> rankResults = initializeRankResults();
        lottoPurchaseResult.purchasedLottos()
                .forEach(purchasedLotto -> {
                    int matchCount = purchasedLotto.countMatches(this);
                    boolean hasBonusNumber = purchasedLotto.contains(bonusNumber);
                    Rank rank = Rank.calculateRank(matchCount, hasBonusNumber);
                    rankResults.put(rank, rankResults.get(rank) + 1);
                });
        return rankResults;
    }

    private Map<Rank, Integer> initializeRankResults() {
        Map<Rank, Integer> rankResults = new HashMap<>();
        Arrays.stream(Rank.values())
                .forEach(rank -> rankResults.putIfAbsent(rank, 0));
        return rankResults;
    }
}
