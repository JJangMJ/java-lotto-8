package lotto.validator;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import lotto.domain.WinningNumbers;
import lotto.exception.ErrorMessage;

public class Validator {
    private static final int LOTTO_PRICE = 1000;
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private static final int LOTTO_SIZE = 6;
    private static final String DELIMITER = ",";
    private static final int BONUS_NUMBER_SIZE = 1;

    public static void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_BLANK.getMessage());
        }
    }

    public static int validateParseToInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.SHOULD_BE_NUMBER.getMessage());
        }
    }

    public static void validateNumbersRange(List<Integer> numbers) {
        numbers.forEach(Validator::validateNumberRange);
    }

    public static void validateNumberRange(int input) {
        if (input < LOTTO_MIN_NUMBER || input > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.SHOULD_BE_IN_RANGE.getMessage());
        }
    }

    public static void validateDuplicateNumber(List<Integer> numbers) {
        Set<Integer> uniqueNumbers = Set.copyOf(numbers);
        if (uniqueNumbers.size() != numbers.size()) {
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_DUPLICATED.getMessage());
        }
    }

    public static void validateNumberCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.SHOULD_BE_SIX_NUMBER.getMessage());
        }
    }

    public static void validateMultipleOfThousand(int input) {
        if (input % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ErrorMessage.SHOULD_BE_MULTIPLE_OF_1000.getMessage());
        }
    }

    public static void validateNonNegative(int input) {
        if (input < 0) {
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_NEGATIVE.getMessage());
        }
    }

    public static void validateDuplicateWinningNumbers(int input, WinningNumbers winningNumbers) {
        if (winningNumbers.contains(input)) {
            throw new IllegalArgumentException(ErrorMessage.CANNOT_BE_DUPLICATED_WITH_WINNING_NUMBERS.getMessage());
        }
    }

    public static void validateSingleNumber(String input) {
        long count = Arrays.stream(input.split(DELIMITER))
                .map(String::trim)
                .filter(string -> !string.isEmpty())
                .count();
        if (count != BONUS_NUMBER_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.SHOULD_BE_SINGLE_NUMBER.getMessage());
        }
    }
}
