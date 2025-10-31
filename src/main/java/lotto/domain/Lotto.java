package lotto.domain;

import java.util.List;
import lotto.exception.ErrorMessage;
import lotto.validator.Validator;

public class Lotto {
    private static final int LOTTO_SIZE = 6;
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        Validator.validateDuplicateNumber(numbers);
        Validator.validateNumbersRange(numbers);
        this.numbers = numbers;
    }

    public int countMatches(WinningNumbers winningNumbers) {
        return (int) numbers.stream().filter(winningNumbers::contains).count();
    }

    public boolean contains(BonusNumber bonusNumber) {
        return numbers.contains(bonusNumber.getNumber());
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.SHOULD_BE_SIX_NUMBER.getMessage());
        }
    }

    public List<Integer> getNumbers() {
        return numbers;
    }
}
