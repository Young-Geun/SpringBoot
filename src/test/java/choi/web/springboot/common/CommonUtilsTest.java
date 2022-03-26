package choi.web.springboot.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommonUtilsTest {

    CommonUtils commonUtils = new CommonUtils();

    @Test
    void 숫자포맷_테스트() {
        assertEquals(commonUtils.numberFormat(123), "123");
        assertEquals(commonUtils.numberFormat(123.0), "123");
        assertEquals(commonUtils.numberFormat(123.00), "123");
        assertEquals(commonUtils.numberFormat(123.01), "123.01");
        assertEquals(commonUtils.numberFormat(123.10), "123.1");

        assertEquals(commonUtils.numberFormat(1234), "1,234");
        assertEquals(commonUtils.numberFormat(1234.0), "1,234");
        assertEquals(commonUtils.numberFormat(1234.00), "1,234");
        assertEquals(commonUtils.numberFormat(1234.01), "1,234.01");
        assertEquals(commonUtils.numberFormat(1234.10), "1,234.1");
    }

}
