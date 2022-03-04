package choi.web.springboot.common;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

@Component
public class CommonUtils {

    public String numberFormat(Object num) {
        try {
            DecimalFormat df = new DecimalFormat("###,###.##");
            return df.format(num);
        } catch (Exception e) {
            return "0";
        }
    }

}
