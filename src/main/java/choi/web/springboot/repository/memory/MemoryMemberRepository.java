package choi.web.springboot.repository.memory;

import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberRepository {

    /* 일반적인 필드 사용 */
    String nameStorage;

    public void save(String name) {
        nameStorage = name;
    }

    public String find() {
        return nameStorage;
    }




    /* ThreadLocal 필드 사용 */
    ThreadLocal<String> threadLocalNameStorage = new ThreadLocal<>();

    public void saveWithThreadLocal(String name) {
        threadLocalNameStorage.set(name);
    }

    public String findWithThreadLocal() {
        return threadLocalNameStorage.get();
    }

}
