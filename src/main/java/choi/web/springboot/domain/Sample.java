package choi.web.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sample {

    long sampleId;
    String sampleName;
    int sampleType;
    long sampleNum1;
    long sampleNum2;
    long sampleNum3;

}
