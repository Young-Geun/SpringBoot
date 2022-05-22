package choi.web.springboot.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MainDao {

    @Autowired
    @Qualifier(value = "sqlSessionFactoryMain")
    private SqlSessionFactory sqlSessionFactory;

    public String selectMainResult() {
        SqlSession session = sqlSessionFactory.openSession();
        try {
            return session.selectOne("selectMainResult");
        } finally {
            session.close();
        }
    }

}
