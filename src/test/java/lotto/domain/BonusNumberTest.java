package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class BonusNumberTest {

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 46, 100})
    void 보너스_번호는_1부터_45_사이여야_한다(int input) {
        //given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        //when & then
        assertThatThrownBy(() -> new BonusNumber(input, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.BONUS_NUMBER_SHOULD_BE_BETWEEN_1_AND_45.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"7,8", "7, 8", "1,2,3",})
    void 보너스_번호가_여러개면_예외_처리한다(String input) {
        assertThatThrownBy(() -> Parser.parseBonusNumber(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.BONUS_NUMBER_SHOULD_BE_SINGLE_NUMBER.getMessage());
    }

    @Test
    void 당첨_번호에_있는_숫자를_보너스_번호로_입력하면_예외_처리한다() {
        //given
        WinningNumbers winningNumbers = new WinningNumbers(List.of(1, 2, 3, 4, 5, 6));

        //when & then
        assertThatThrownBy(() -> new BonusNumber(1, winningNumbers))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.BONUS_NUMBER_SHOULD_NOT_DUPLICATE_WINNING_NUMBERS.getMessage());
    }
}
