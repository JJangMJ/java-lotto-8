package lotto.view;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoPurchaseAmount;

public class OutputView {

    public void printPurchasedLottos(LottoPurchaseAmount purchaseAmount, List<Lotto> purchasedLottos) {
        System.out.println(purchaseAmount.value() + "개를 구매했습니다.");
        purchasedLottos.forEach(purchasedLotto -> System.out.println(purchasedLotto.getNumbers()));
    }
}
