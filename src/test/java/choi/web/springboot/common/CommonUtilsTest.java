package choi.web.springboot.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonUtilsTest {

    CommonUtils commonUtils = new CommonUtils();

    @Test
    void 숫자포맷_테스트() {
        assertEquals("123", commonUtils.numberFormat(123));
        assertEquals("123", commonUtils.numberFormat(123.0));
        assertEquals("123", commonUtils.numberFormat(123.00));
        assertEquals("123.01", commonUtils.numberFormat(123.01));
        assertEquals("123.1", commonUtils.numberFormat(123.10));

        assertEquals("1,234", commonUtils.numberFormat(1234));
        assertEquals("1,234", commonUtils.numberFormat(1234.0));
        assertEquals("1,234", commonUtils.numberFormat(1234.00));
        assertEquals("1,234.01", commonUtils.numberFormat(1234.01));
        assertEquals("1,234.1", commonUtils.numberFormat(1234.10));
    }

}
