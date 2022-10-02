package choi.web.springboot.common;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class CommonUtils {

    /**
     * 숫자포맷 변경
     *
     * @param num
     * @return
     */
    public String numberFormat(Object num) {
        try {
            DecimalFormat df = new DecimalFormat("###,###.##");
            return df.format(num);
        } catch (Exception e) {
            return "0";
        }
    }

    /**
     * 날짜 포맷 변경
     * Ex) dateStr = 20221002, format = "-"
     * => 2022-10-02
     *
     * @param dateStr
     * @param format
     * @return
     */
    public String dateFormatYmd(String dateStr, String format) {
        try {
            return dateStr.substring(0, 4) + format + dateStr.substring(4, 6) + format + dateStr.substring(6, 8);
        } catch (Exception e) {
            return dateStr;
        }
    }

}
