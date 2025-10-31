package lotto.view;

import java.util.Arrays;
import java.util.Map;
import lotto.domain.Rank;
import lotto.dto.LottoPurchaseResult;

public class OutputView {

    public void printPurchasedLottos(LottoPurchaseResult purchaseResult) {
        System.out.println(purchaseResult.purchaseCount() + "개를 구매했습니다.");
        purchaseResult.purchasedLottos().forEach(purchasedLotto -> System.out.println(purchasedLotto.getNumbers()));
        System.out.println();
    }

    public void printWinningStatistics(Map<Rank, Integer> rankResults) {
        System.out.println("당첨 통계");
        System.out.println("---");
        Arrays.stream(Rank.values())
                .filter(rank -> rank != Rank.NOTHING)
                .forEach(rank -> System.out.println(rank.getMatchDescription() + " - " + rankResults.get(rank)));
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
