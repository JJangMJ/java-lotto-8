package lotto.domain;

import lotto.exception.ErrorMessage;

public class LottoPurchaseAmount {
    private static final int UNIT = 1000;
    private final int purchaseAmount;

    private LottoPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public static LottoPurchaseAmount from(String value) {
        validateBlank(value);
        int purchaseAmount = validateNumber(value);
        validateNonNegative(purchaseAmount);
        validateMultipleOfThousand(purchaseAmount);
        return new LottoPurchaseAmount(purchaseAmount);
    }

    public int value() {
        return value();
    }

    private static void validateBlank(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_CANNOT_BE_BLANK.getMessage());
        }
    }

    private static int validateNumber(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_SHOULD_BE_NUMBER.getMessage());
        }
    }

    private static void validateNonNegative(int purchaseAmount) {
        if (purchaseAmount < 0) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_CANNOT_BE_NEGATIVE.getMessage());
        }
    }

    private static void validateMultipleOfThousand(int purchaseAmount) {
        if (purchaseAmount % UNIT != 0) {
            throw new IllegalArgumentException(ErrorMessage.PURCHASE_AMOUNT_SHOULD_BE_MULTIPLE_OF_1000.getMessage());
        }
    }
}
