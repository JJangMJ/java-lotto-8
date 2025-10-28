package lotto.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String inputLottoPurchasePrice() {
        System.out.println("구입금액을 입력해 주세요.");
        String lottoPurchasePrice = Console.readLine();
        System.out.println();
        return lottoPurchasePrice;
    }
}
