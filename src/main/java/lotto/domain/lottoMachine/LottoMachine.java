package lotto.domain.lottoMachine;

import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.lotto.Lotto;

public class LottoMachine {
    private static final int ONE_LOTTO_PRICE = 1000;
    private final LottoPurchaseAmount purchaseAmount;

    public LottoMachine(LottoPurchaseAmount purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public List<Lotto> generateLottos() {
        int purchaseCount = purchaseAmount.value() / ONE_LOTTO_PRICE;
        List<Integer> numbers = List.of(1,2,3,4,5,6);
        return IntStream.range(0, purchaseCount)
                .mapToObj(i -> new Lotto(numbers))
                .toList();
    }
}
