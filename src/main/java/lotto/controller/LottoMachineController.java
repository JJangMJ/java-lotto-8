package lotto.controller;

import java.util.List;
import java.util.Map;
import lotto.domain.BonusNumber;
import lotto.domain.LottoMachine;
import lotto.domain.LottoPurchaseAmount;
import lotto.domain.Parser;
import lotto.domain.Rank;
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
        LottoPurchaseResult lottoPurchaseResult = purchaseLottos();
        WinningNumbers winningNumbers = generateWinningNumbers();
        BonusNumber bonusNumber = generateBonusNumber(winningNumbers);
        printWinningStatistics(lottoPurchaseResult, winningNumbers, bonusNumber);
    }

    private LottoPurchaseResult purchaseLottos() {
        while (true) {
            try {
                LottoPurchaseAmount purchaseAmount = LottoPurchaseAmount.from(inputView.inputLottoPurchasePrice());
                RandomLottoNumberGenerator randomLottoNumberGenerator = new RandomLottoNumberGenerator();
                LottoMachine lottoMachine = new LottoMachine(purchaseAmount, randomLottoNumberGenerator);
                LottoPurchaseResult lottoPurchaseResult = lottoMachine.generateLottos();
                outputView.printPurchasedLottos(lottoPurchaseResult);
                return lottoPurchaseResult;
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private WinningNumbers generateWinningNumbers() {
        while (true) {
            try {
                List<Integer> numbers = Parser.parseWinningNumbers(inputView.inputWinningNumbers());
                return new WinningNumbers(numbers);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private BonusNumber generateBonusNumber(WinningNumbers winningNumbers) {
        while (true) {
            try {
                int number = Parser.parseBonusNumber(inputView.inputBonusNumber());
                return new BonusNumber(number, winningNumbers);
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }

    private void printWinningStatistics(LottoPurchaseResult lottoPurchaseResult, WinningNumbers winningNumbers,
                                        BonusNumber bonusNumber) {
        while (true) {
            try {
                Map<Rank, Integer> rankResults = winningNumbers.getRankResults(lottoPurchaseResult, bonusNumber);
                outputView.printWinningStatistics(rankResults);
                break;
            } catch (IllegalArgumentException exception) {
                outputView.printErrorMessage(exception.getMessage());
            }
        }
    }
}
