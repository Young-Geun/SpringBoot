package choi.web.springboot.common;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;

@Component
public class SecurityUtils {

    public String encrypt(String text) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(text.getBytes());

        byte[] bytes = md.digest();

        StringBuffer buffer = new StringBuffer();
        for (byte b : bytes) {
            buffer.append(String.format("%02x", b));
        }

        return buffer.toString().toUpperCase();
    }

}
