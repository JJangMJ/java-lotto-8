package lotto.domain;

import java.util.List;
import java.util.Set;
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

    public int countMatches(List<Integer> lottoNumbers) {
        return (int) lottoNumbers.stream()
                .filter(numbers::contains)
                .count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
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
