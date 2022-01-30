package choi.web.springboot.repository;

import choi.web.springboot.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

@Mapper
@Transactional
public interface UserRepository {

    User selectUser(User user);

    User selectExistUser(User user);

    int registUser(User user);

    int editUser(User user);

}
