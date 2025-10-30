package lotto.domain;

import java.util.List;
import lotto.exception.ErrorMessage;

public class BonusNumber {
    private static final int LOTTO_MIN_NUMBER = 1;
    private static final int LOTTO_MAX_NUMBER = 45;
    private final int number;

    public BonusNumber(int number, WinningNumbers winningNumbers) {
        validateNumberRange(number);
        validateNotDuplicateWith(number, winningNumbers);
        this.number = number;
    }

    public boolean isContainedIn(List<Integer> numbers) {
        return numbers.contains(number);
    }

    private void validateNumberRange(int number) {
        if (number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.BONUS_NUMBER_SHOULD_BE_BETWEEN_1_AND_45.getMessage());
        }
    }

    private void validateNotDuplicateWith(int number, WinningNumbers winningNumbers) {
        if (winningNumbers.contains(number)) {
            throw new IllegalArgumentException(
                    ErrorMessage.BONUS_NUMBER_SHOULD_NOT_DUPLICATE_WINNING_NUMBERS.getMessage());
        }
    }
}
