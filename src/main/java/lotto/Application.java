package lotto;

import lotto.controller.LottoMachineController;
import lotto.view.InputView;
import lotto.view.OutputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        LottoMachineController lottoMachineController = new LottoMachineController(inputView, outputView);
        lottoMachineController.run();
    }
}
