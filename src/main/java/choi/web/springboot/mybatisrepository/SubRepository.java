package choi.web.springboot.mybatisrepository;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class SubRepository {

    @Autowired
    @Qualifier(value = "sqlSessionFactorySub")
    private SqlSessionFactory sqlSessionFactory;

    public String selectSubResult() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            return session.selectOne("selectSubResult");
        } finally {
            session.close();
        }
    }

}
