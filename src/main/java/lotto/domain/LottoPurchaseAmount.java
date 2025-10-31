package lotto.domain;

import lotto.validator.Validator;

public class LottoPurchaseAmount {
    private final int purchaseAmount;

    private LottoPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public static LottoPurchaseAmount from(String input) {
        Validator.validateBlank(input);
        int purchaseAmount = Validator.validateParseToInt(input);
        Validator.validateNonNegative(purchaseAmount);
        Validator.validateMultipleOfThousand(purchaseAmount);
        return new LottoPurchaseAmount(purchaseAmount);
    }

    public int value() {
        return purchaseAmount;
    }
}
