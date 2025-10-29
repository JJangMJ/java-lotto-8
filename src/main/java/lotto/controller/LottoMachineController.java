package lotto.controller;

import lotto.domain.LottoMachine;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.RandomLottoNumberGenerator;
import lotto.dto.LottoPurchaseResult;
import lotto.view.InputView;
import lotto.view.OutputView;

public class LottoMachineController {
    private final InputView inputView;
    private final OutputView outputView;

    public LottoMachineController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        while (true) {
            try {
                purchaseLottos();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private void purchaseLottos() {
        LottoPurchaseAmount purchaseAmount = LottoPurchaseAmount.from(inputView.inputLottoPurchasePrice());
        RandomLottoNumberGenerator randomLottoNumberGenerator = new RandomLottoNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(purchaseAmount, randomLottoNumberGenerator);
        LottoPurchaseResult lottoPurchaseResult = lottoMachine.generateLottos();
        outputView.printPurchasedLottos(lottoPurchaseResult);
    }
}
