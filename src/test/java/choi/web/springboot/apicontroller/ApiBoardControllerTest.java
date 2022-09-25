package choi.web.springboot.apicontroller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "mac")
@AutoConfigureMockMvc
@AutoConfigureRestDocs
class ApiBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Description("게시글 조회")
    public void findByBoardId() throws Exception {
        this.mockMvc.perform(get("/api/board/150").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("find-board", responseFields(
                        fieldWithPath("responseCode").description("응답 코드"),
                        fieldWithPath("responseMsg").description("응답 메시지"),
                        fieldWithPath("responseData").description("응답 데이터"),
                        fieldWithPath("responseData.boardId").description("게시글 번호"),
                        fieldWithPath("responseData.title").description("게시글 제목"),
                        fieldWithPath("responseData.contents").description("게시글 내용"),
                        fieldWithPath("responseData.regDate").description("게시글 등록일"),
                        fieldWithPath("responseData.poster").description("작성자 정보"),
                        fieldWithPath("responseData.poster.memberId").description("작성자 아이디"),
                        fieldWithPath("responseData.poster.memberEmail").description("작성자 이메일"),
                        fieldWithPath("responseData.poster.memberName").description("작성자 이름"),
                        fieldWithPath("responseData.poster.memberProfile").description("작성자 프로필 경로"),
                        fieldWithPath("responseData.poster.memberStatus").description("작성자 상태"),
                        fieldWithPath("responseData.poster.lastLoginDate").description("작성자 마지막 로그인 일시")
                )));
    }

}