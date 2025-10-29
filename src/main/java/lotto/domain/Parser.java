package lotto.domain;

import java.util.List;
import lotto.exception.ErrorMessage;

public class Parser {
    private static final String DELIMITER = ",";

    public static List<Integer> parseWinningNumbers(String input) {
        validateBlank(input);
        List<String> splitString = List.of(input.split(DELIMITER));
        return parseStringToInteger(splitString);
    }

    public static int parseBonusNumber(String input) {
        validateBlank(input);
        return validateParseToInt(input);
    }

    private static List<Integer> parseStringToInteger(List<String> parseString) {
        return parseString.stream()
                .map(Parser::validateParseToInt)
                .toList();
    }

    private static int validateParseToInt(String input) {
        try {
            return Integer.parseInt(input);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBERS_SHOULD_BE_NUMBER.getMessage());
        }
    }

    private static void validateBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.WINNING_NUMBERS_CANNOT_BE_BLANK.getMessage());
        }
    }
}
