package choi.web.springboot.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
@MapperScan(basePackages = {"choi.web.springboot"})
public class DataSourceConfig {

    /**
     * DataSource Main 생성
     */
    @Bean
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource mainDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    /**
     * DataSource Sub 생성
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource-sub")
    public DataSource subDataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    /**
     * sqlSessionFactory Main 생성
     */
    @Bean
    @Primary
    public SqlSessionFactory sqlSessionFactoryMain(@Autowired @Qualifier("mainDataSource") DataSource dataSource) throws Exception {
        org.apache.ibatis.session.Configuration configuration = this.getMybatisConfig();

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setVfs(SpringBootVFS.class);
        factoryBean.setConfiguration(configuration);
        factoryBean.setTypeHandlersPackage("com.commax.tool.framework.mybatis.typehandler");

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resource = resolver.getResources("mapper/**/*.xml");
        factoryBean.setMapperLocations(resource);

        return factoryBean.getObject();
    }

    /**
     * sqlSessionFactory Sub 생성
     */
    @Bean
    public SqlSessionFactory sqlSessionFactorySub(@Autowired @Qualifier("subDataSource") DataSource dataSource) throws Exception {
        org.apache.ibatis.session.Configuration configuration = this.getMybatisConfig();

        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        factoryBean.setVfs(SpringBootVFS.class);
        factoryBean.setConfiguration(configuration);
        factoryBean.setTypeHandlersPackage("com.commax.tool.framework.mybatis.typehandler");

        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resource = resolver.getResources("mapper/**/*.xml");
        factoryBean.setMapperLocations(resource);

        return factoryBean.getObject();
    }

    /**
     * sqlSession Main 생성
     */
    @Bean
    @Primary
    public SqlSession sqlSessionMain(@Autowired @Qualifier("sqlSessionFactoryMain") SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }

    /**
     * sqlSession Sub 생성
     */
    @Bean
    public SqlSession sqlSessionSub(@Autowired @Qualifier("sqlSessionFactorySub") SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
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