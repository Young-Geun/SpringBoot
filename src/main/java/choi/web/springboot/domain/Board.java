package choi.web.springboot.domain;

public class Board {

    // �Խù� ���̵�
    int boardId;

    // ����
    String title;

    // ����
    String contents;

    // �����
    String regId;

    // ����Ͻ�
    String regDate;

    // �����Խ��� ����
    String notiFlag;

    public int getBoardId() {
        return boardId;
    }

    public void setBoardId(int boardId) {
        this.boardId = boardId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getNotiFlag() {
        return notiFlag;
    }

    public void setNotiFlag(String notiFlag) {
        this.notiFlag = notiFlag;
    }

}
