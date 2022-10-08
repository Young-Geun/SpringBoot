package choi.web.springboot.common;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.Properties;
import java.util.Random;

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

    /**
     * 메일 발송
     *
     * @param recipient
     * @param subject
     * @param text
     * @return
     */
    public void sendMail(String recipient, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(recipient);
        message.setSubject(subject);
        message.setText(text);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);

        mailSender.setHost("smtp.gmail.com");
        mailSender.setUsername("younggeunnn@gmail.com");
        /*
            패스워드 발급 방법
            1. Google 계정 메뉴 접근
            2. 'Google에 로그인' 영역 > 앱 비밀번호
            3. 앱 비밀번호 > 앱 선택(Selectbox) > 메일 선택
         */
        mailSender.setPassword("1234");
        mailSender.setPort(587);
        mailSender.setJavaMailProperties(properties);

        mailSender.send(message);
    }

    /**
     * N 자릿수의 랜덤 문자열 만들기
     *
     * @param length
     * @return
     */
    public String generateRandomString(int length) {
        return new Random().ints(97, 123)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
