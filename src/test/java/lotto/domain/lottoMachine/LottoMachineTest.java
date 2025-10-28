package lotto.domain.lottoMachine;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.lotto.Lotto;
import org.junit.jupiter.api.Test;

public class LottoMachineTest {
    @Test
    void 구입_금액을_기준으로_발행할_로또의_수량을_계산한다() {
        //given
        LottoPurchaseAmount purchaseAmount = LottoPurchaseAmount.from("8000");

        //when
        LottoMachine lottoMachine = new LottoMachine(purchaseAmount);
        List<Lotto> lottos = lottoMachine.generateLottos();

        //then
        assertThat(lottos).hasSize(8);
    }
}
