package choi.web.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sample {

    int sampleId;
    long numColumn1;
    long numColumn2;
    long numColumn3;
    long numColumn4;
    long numColumn5;
    String strColumn1;
    String strColumn2;
    String strColumn3;
    String strColumn4;
    String strColumn5;
    LocalDateTime dateColumn1;
    LocalDateTime dateColumn2;
    LocalDateTime dateColumn3;

}
