package lotto.exception;

public enum ErrorMessage {
    PURCHASE_AMOUNT_CANNOT_BE_BLANK("구입 금액은 공백일 수 없습니다."),
    PURCHASE_AMOUNT_SHOULD_BE_NUMBER("구입 금액은 숫자여야 합니다."),
    PURCHASE_AMOUNT_CANNOT_BE_NEGATIVE("구입 금액은 음수일 수 없습니다."),
    PURCHASE_AMOUNT_SHOULD_BE_MULTIPLE_OF_1000("구입 금액은 1,000원 단위여야 합니다.");

    private static final String ERROR_MESSAGE_HEADER = "[ERROR] ";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = ERROR_MESSAGE_HEADER + errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}
