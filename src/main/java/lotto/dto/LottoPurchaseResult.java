package lotto.dto;

import java.util.List;
import lotto.domain.Lotto;

public record LottoPurchaseResult(int purchaseCount, List<Lotto> purchasedLottos) {
}
