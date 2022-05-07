package choi.web.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Test {

    long testId;
    String testName;
    int testType;
    long testNum1;
    long testNum2;
    long testNum3;

}
