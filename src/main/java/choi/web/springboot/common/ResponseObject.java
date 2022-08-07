package choi.web.springboot.common;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ResponseObject {

    String responseCode;

    String responseMsg;

    Object responseData;

}
