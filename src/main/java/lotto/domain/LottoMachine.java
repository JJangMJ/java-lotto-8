package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;

public class LottoMachine {
    private static final int ONE_LOTTO_PRICE = 1000;
    private final int purchaseAmount;

    public LottoMachine(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public List<Lotto> generateLottos() {
        int purchaseCount = purchaseAmount / ONE_LOTTO_PRICE;
        List<Integer> numbers = List.of(1,2,3,4,5,6);
        return IntStream.range(0, purchaseCount)
                .mapToObj(i -> new Lotto(numbers))
                .toList();
    }
}
