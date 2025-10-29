package lotto.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import lotto.exception.ErrorMessage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class WinningNumbersTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  \t"})
    void 당첨_번호가_공백이면_예외_처리한다(String input) {
        assertThatThrownBy(() -> new WinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WINNING_NUMBERS_CANNOT_BE_BLANK.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,5", "1,2,3,4,5,6,7"})
    void 당첨_번호의_숫자_개수가_6개가_아니면_예외_처리한다(String input) {
        assertThatThrownBy(() -> new WinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WINNING_NUMBERS_SHOULD_BE_SIX_NUMBERS.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"0,1,2,3,4,5", "1,2,3,4,5,46"})
    void 당첨_번호의_각_숫자는_1부터_45_사이여야_한다(String input) {
        assertThatThrownBy(() -> new WinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WINNING_NUMBERS_SHOULD_BE_BETWEEN_1_AND_45.getMessage());
    }

    @Test
    void 당첨_번호의_숫자_중_중복_숫자가_있으면_예외_처리한다() {
        assertThatThrownBy(() -> new WinningNumbers("1,2,3,3,4,5"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WINNING_NUMBERS_CANNOT_BE_DUPLICATED.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"1,2,3,4,a,6", "1,2,3,4,5,@"})
    void 숫자가_아닌_값이_있으면_예외(String input) {
        assertThatThrownBy(() -> new WinningNumbers(input))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.WINNING_NUMBERS_SHOULD_BE_NUMBER.getMessage());
    }
}
