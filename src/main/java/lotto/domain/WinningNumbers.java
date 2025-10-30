package lotto.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lotto.dto.LottoPurchaseResult;
import lotto.exception.ErrorMessage;

public class WinningNumbers {
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;
    private final List<Integer> numbers;

    public WinningNumbers(List<Integer> numbers) {
        validateNumberCount(numbers);
        validateNumbersRange(numbers);
        validateDuplicateNumber(numbers);
        this.numbers = numbers;
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public Map<Rank, Integer> getRankResults(LottoPurchaseResult lottoPurchaseResult, BonusNumber bonusNumber) {
        Map<Rank, Integer> rankResults = initializeRankResults();
        lottoPurchaseResult.purchasedLottos()
                .forEach(purchasedLotto -> {
                    int matchCount = countMatches(purchasedLotto.getNumbers());
                    boolean hasBonusNumber = bonusNumber.isContainedIn(purchasedLotto.getNumbers());
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

    private int countMatches(List<Integer> lottoNumbers) {
        return (int) lottoNumbers.stream()
                .filter(numbers::contains)
                .count();
    }

    private void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBERS_SHOULD_BE_SIX_NUMBERS.getMessage());
        }
    }

    private void validateNumbersRange(List<Integer> numbers) {
        numbers.forEach(this::validateNumberRange);
    }

    private void validateNumberRange(int number) {
        if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBERS_SHOULD_BE_BETWEEN_1_AND_45.getMessage());
        }
    }

    private void validateDuplicateNumber(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = Set.copyOf(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBERS_CANNOT_BE_DUPLICATED.getMessage());
        }
    }
}
