package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;
import lotto.domain.generator.LottoNumberGenerator;
import lotto.dto.LottoPurchaseResult;

public class LottoMachine {
    private static final int ONE_LOTTO_PRICE = 1000;
    private final LottoPurchaseAmount purchaseAmount;
    private final LottoNumberGenerator lottoNumberGenerator;

    public LottoMachine(LottoPurchaseAmount purchaseAmount, LottoNumberGenerator lottoNumberGenerator) {
        this.purchaseAmount = purchaseAmount;
        this.lottoNumberGenerator = lottoNumberGenerator;
    }

    public LottoPurchaseResult generateLottos() {
        int purchaseCount = purchaseAmount.value() / ONE_LOTTO_PRICE;
        List<Lotto> purchasedLottos = IntStream.range(0, purchaseCount)
                .mapToObj(index -> new Lotto(lottoNumberGenerator.generate()))
                .toList();
        return new LottoPurchaseResult(purchaseCount, purchasedLottos);
    }
}
