package choi.web.springboot.repository.mybatissub;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleSubRepository {

    String findResult();

}
