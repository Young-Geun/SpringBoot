package choi.web.springboot.config;

import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
@MapperScan(value="choi.web.springboot.repository.mybatissub", sqlSessionFactoryRef="subSqlSessionFactory")
@EnableTransactionManagement
public class SubDataSourceConfig {

    @Bean(name = "dataSourceSub")
    @ConfigurationProperties(prefix = "spring.datasource-sub")

    public DataSource dataSourceSub() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "subSqlSessionFactory")
    public SqlSessionFactory subSqlSessionFactory(@Qualifier("dataSourceSub") DataSource dataSourceSub) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSourceSub);
        sqlSessionFactoryBean.setConfiguration(getMybatisConfig());
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resource = resolver.getResources("mapper/sub/**/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resource);
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "subSqlSessionTemplate")
    public SqlSessionTemplate subSqlSessionTemplate(SqlSessionFactory subSqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(subSqlSessionFactory);
    }

    /**
     * MybatisConfig 설정정보
     */
    private org.apache.ibatis.session.Configuration getMybatisConfig() {
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(true);
        configuration.setLazyLoadingEnabled(false);
        configuration.setAggressiveLazyLoading(false);
        configuration.setMultipleResultSetsEnabled(true);
        configuration.setUseColumnLabel(true);
        configuration.setAutoMappingBehavior(AutoMappingBehavior.PARTIAL);
        configuration.setDefaultExecutorType(ExecutorType.SIMPLE);
        configuration.setDefaultStatementTimeout(25000);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setJdbcTypeForNull(JdbcType.NVARCHAR);
        configuration.setLazyLoadTriggerMethods(new HashSet<>(Arrays.asList("equals", "clone", "hashCode", "toString")));
        configuration.setLogPrefix("[SQL]");

        return configuration;
    }

}