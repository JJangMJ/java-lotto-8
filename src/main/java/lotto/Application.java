package lotto;

import lotto.controller.LottoMachineController;
import lotto.view.InputView;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();

        LottoMachineController lottoMachineController = new LottoMachineController(inputView);
        lottoMachineController.run();
    }
}
