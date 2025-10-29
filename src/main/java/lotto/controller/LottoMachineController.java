package lotto.controller;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.RandomLottoNumberGenerator;
import lotto.view.InputView;

public class LottoMachineController {
    private final InputView inputView;

    public LottoMachineController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        while (true) {
            try {
                List<Lotto> purchasedLottos = purchaseLottos();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private List<Lotto> purchaseLottos() {
        LottoPurchaseAmount purchaseAmount = LottoPurchaseAmount.from(inputView.inputLottoPurchasePrice());
        RandomLottoNumberGenerator randomLottoNumberGenerator = new RandomLottoNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(purchaseAmount, randomLottoNumberGenerator);
        return lottoMachine.generateLottos();
    }
}
