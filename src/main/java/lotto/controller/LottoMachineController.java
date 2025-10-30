package lotto.controller;

import java.util.List;
import lotto.domain.BonusNumber;
import lotto.domain.LottoMachine;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.Parser;
import lotto.domain.WinningNumbers;
import lotto.domain.generator.RandomLottoNumberGenerator;
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
                LottoPurchaseResult lottoPurchaseResult = purchaseLottos();
                WinningNumbers winningNumbers = generateWinningNumbers();
                BonusNumber bonusNumber = generateBonusNumber(winningNumbers);
            } catch (IllegalArgumentException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    private LottoPurchaseResult purchaseLottos() {
        LottoPurchaseAmount purchaseAmount = LottoPurchaseAmount.from(inputView.inputLottoPurchasePrice());
        RandomLottoNumberGenerator randomLottoNumberGenerator = new RandomLottoNumberGenerator();
        LottoMachine lottoMachine = new LottoMachine(purchaseAmount, randomLottoNumberGenerator);
        LottoPurchaseResult lottoPurchaseResult = lottoMachine.generateLottos();
        outputView.printPurchasedLottos(lottoPurchaseResult);
        return lottoPurchaseResult;
    }

    private WinningNumbers generateWinningNumbers() {
        List<Integer> numbers = Parser.parseWinningNumbers(inputView.inputWinningNumbers());
        return new WinningNumbers(numbers);
    }

    private BonusNumber generateBonusNumber(WinningNumbers winningNumbers) {
        int number = Parser.parseBonusNumber(inputView.inputBonusNumber());
        return new BonusNumber(number, winningNumbers);
    }
}
