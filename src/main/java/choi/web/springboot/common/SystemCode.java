package choi.web.springboot.common;

public enum SystemCode {

    SUCCESS_COMMON("S000", "성공했습니다."),
    FAIL_COMMON("F000", "실패했습니다."),
    FAIL_SEARCH("F001", "조회된 값이 없습니다."),
    FAIL_INSERT("F002", "등록을 실패했습니다."),
    FAIL_UPDATE("F003", "수정을 실패했습니다."),
    FAIL_DELETE("F004", "삭제를 실패했습니다."),
    FAIL_INVALID_VALUE("F005", "유효한 값이 아닙니다."),
    FAIL_ALREADY_EXIST_VALUE("F005", "이미 존재하는 데이터입니다."),
    ERROR_COMMON("E000", "오류가 발생했습니다.");

    private String code;
    private String message;

    SystemCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

}
