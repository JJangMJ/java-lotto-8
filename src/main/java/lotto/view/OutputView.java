package lotto.view;

import lotto.dto.LottoPurchaseResult;

public class OutputView {

    public void printPurchasedLottos(LottoPurchaseResult purchaseResult) {
        System.out.println(purchaseResult.purchaseCount() + "개를 구매했습니다.");
        purchaseResult.purchasedLottos().forEach(purchasedLotto -> System.out.println(purchasedLotto.getNumbers()));
        System.out.println();
    }
}
