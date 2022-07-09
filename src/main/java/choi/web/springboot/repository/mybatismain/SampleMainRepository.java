package choi.web.springboot.repository.mybatismain;

import choi.web.springboot.domain.Sample;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SampleMainRepository {

    Sample findByFirstRow();

    int insert(Sample sample);

    int update(Sample sample);

    String findResult();

}
