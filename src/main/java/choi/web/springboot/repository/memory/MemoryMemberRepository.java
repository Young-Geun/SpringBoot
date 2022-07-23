package choi.web.springboot.repository.memory;

import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberRepository {

    /* �Ϲ����� �ʵ� ��� */
    String nameStorage;

    public void save(String name) {
        nameStorage = name;
    }

    public String find() {
        return nameStorage;
    }




    /* ThreadLocal �ʵ� ��� */
    ThreadLocal<String> threadLocalNameStorage = new ThreadLocal<>();

    public void saveWithThreadLocal(String name) {
        threadLocalNameStorage.set(name);
    }

    public String findWithThreadLocal() {
        return threadLocalNameStorage.get();
    }

}
