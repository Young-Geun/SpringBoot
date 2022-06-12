package choi.web.springboot.domain.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseData {

    @JsonProperty("CODE")
    String code;

    @JsonProperty("MESSAGE")
    String  message;

    @JsonProperty("DATA")
    List<? extends Object> data;

}
