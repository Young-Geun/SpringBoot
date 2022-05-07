package choi.web.springboot.repository;

import choi.web.springboot.domain.Sample;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MybatisRepository {

    Sample findByFirstRow();

    int update(Sample sample);

}
