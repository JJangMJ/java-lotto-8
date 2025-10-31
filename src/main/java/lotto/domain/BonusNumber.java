package lotto.domain;

import java.util.List;
import lotto.validator.Validator;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number, WinningNumbers winningNumbers) {
        Validator.validateNumberRange(number);
        Validator.validateDuplicateWinningNumbers(number, winningNumbers);
        this.number = number;
    }

    public boolean isContainedIn(List<Integer> numbers) {
        return numbers.contains(number);
    }
}
