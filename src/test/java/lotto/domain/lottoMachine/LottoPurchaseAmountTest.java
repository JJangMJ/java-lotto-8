package lotto.domain.lottoMachine;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class LottoPurchaseAmountTest {
    @Test
    void 로또_구입_금액이_공백이면_예외_처리한다() {
        assertThatThrownBy(() -> LottoPurchaseAmount.from(" "))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PURCHASE_AMOUNT_CANNOT_BE_BLANK.getMessage());
    }

    @Test
    void 로또_구입_금액이_숫자가_아니면_예외_처리한다() {
        assertThatThrownBy(() -> LottoPurchaseAmount.from("lotto"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PURCHASE_AMOUNT_SHOULD_BE_NUMBER.getMessage());
    }

    @Test
    void 로또_구입_금액이_음수이면_예외_처리한다() {
        assertThatThrownBy(() -> LottoPurchaseAmount.from("-1000"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PURCHASE_AMOUNT_CANNOT_BE_NEGATIVE.getMessage());
    }

    @Test
    void 로또_구입_금액이_천원_단위가_아니면_예외_처리한다() {
        assertThatThrownBy(() -> LottoPurchaseAmount.from("1500"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.PURCHASE_AMOUNT_SHOULD_BE_MULTIPLE_OF_1000.getMessage());
    }
}
