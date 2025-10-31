package lotto.domain;

import lotto.validator.Validator;

public class BonusNumber {
    private final int number;

    public BonusNumber(int number, WinningNumbers winningNumbers) {
        Validator.validateNumberRange(number);
        Validator.validateDuplicateWinningNumbers(number, winningNumbers);
        this.number = number;
    }

    public int getNumber() {
        return number;
    }
}
