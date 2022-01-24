package choi.web.springboot.domain;

import lombok.Data;

@Data
public class Todo {

    // 할일 아이디
    int todoId;

    // 내용
    String contents;

    // 등록자
    String regId;

    // 완료 여부
    String completeFlag;

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

}
