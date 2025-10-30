package lotto.exception;

public enum ErrorMessage {
    PURCHASE_AMOUNT_CANNOT_BE_BLANK("구입 금액은 비어있을 수 없습니다."),
    PURCHASE_AMOUNT_SHOULD_BE_NUMBER("구입 금액은 숫자여야 합니다."),
    PURCHASE_AMOUNT_CANNOT_BE_NEGATIVE("구입 금액은 음수일 수 없습니다."),
    PURCHASE_AMOUNT_SHOULD_BE_MULTIPLE_OF_1000("구입 금액은 1,000원 단위여야 합니다."),
    WINNING_NUMBERS_CANNOT_BE_BLANK("당첨 번호는 비어있을 수 없습니다."),
    WINNING_NUMBERS_SHOULD_BE_NUMBER("당첨 번호는 숫자로만 이루어져야 합니다."),
    WINNING_NUMBERS_SHOULD_BE_SIX_NUMBERS("당첨 번호는 6개의 숫자로 이루어져야 합니다."),
    WINNING_NUMBERS_SHOULD_BE_BETWEEN_1_AND_45("당첨 번호는 1~45 사이의 숫자만 입력할 수 있습니다."),
    WINNING_NUMBERS_CANNOT_BE_DUPLICATED("당첨 번호는 중복될 수 없습니다."),
    BONUS_NUMBER_SHOULD_BE_SINGLE_NUMBER("보너스 번호는 하나만 입력해야 합니다."),
    BONUS_NUMBER_SHOULD_BE_BETWEEN_1_AND_45("보너스 번호는 1~45 사이의 숫자만 입력할 수 있습니다."),
    BONUS_NUMBER_SHOULD_NOT_DUPLICATE_WINNING_NUMBERS("보너스 번호는 당첨 번호에 없는 숫자를 입력해야 합니다.");

    private static final String ERROR_MESSAGE_HEADER = "[ERROR] ";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = ERROR_MESSAGE_HEADER + errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}
