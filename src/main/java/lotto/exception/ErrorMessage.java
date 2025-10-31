package lotto.exception;

public enum ErrorMessage {
    CANNOT_BE_BLANK("입력이 비어있습니다."),
    SHOULD_BE_NUMBER("숫자만 입력할 수 있습니다."),
    SHOULD_BE_IN_RANGE("1~45 사이의 숫자만 입력할 수 있습니다."),
    CANNOT_BE_DUPLICATED("중복된 숫자가 있습니다."),
    SHOULD_BE_SIX_NUMBER("6개의 숫자로 이루어져야 합니다."),
    SHOULD_BE_MULTIPLE_OF_1000("1000원 단위로만 구입할 수 있습니다."),
    CANNOT_BE_NEGATIVE("구입 금액은 0보다 큰 숫자만 입력할 수 있습니다."),
    SHOULD_BE_SINGLE_NUMBER("하나의 숫자만 입력해야 합니다."),
    CANNOT_BE_DUPLICATED_WITH_WINNING_NUMBERS("당첨 번호에 없는 숫자를 입력해야 합니다.");


    private static final String ERROR_MESSAGE_HEADER = "[ERROR] ";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = ERROR_MESSAGE_HEADER + errorMessage;
    }

    public String getMessage() {
        return errorMessage;
    }
}
