package lotto.controller;

import lotto.view.InputView;

public class LottoMachineController {
    private final InputView inputView;

    public LottoMachineController(InputView inputView) {
        this.inputView = inputView;
    }

    public void run() {
        while (true) {
            try {
                String lottoPurchasePrice = inputView.inputLottoPurchasePrice();
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }
}
