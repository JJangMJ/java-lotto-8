package lotto.domain;

import java.util.List;
import lotto.validator.Validator;

public class Parser {
    private static final String DELIMITER = ",";

    public static List<Integer> parseWinningNumbers(String input) {
        Validator.validateBlank(input);
        List<String> splitString = List.of(input.split(DELIMITER));
        return parseStringToInteger(splitString);
    }

    public static int parseBonusNumber(String input) {
        Validator.validateBlank(input);
        Validator.validateSingleNumber(input);
        return Validator.validateParseToInt(input);
    }

    private static List<Integer> parseStringToInteger(List<String> parseString) {
        return parseString.stream()
                .map(Validator::validateParseToInt)
                .toList();
    }
}
