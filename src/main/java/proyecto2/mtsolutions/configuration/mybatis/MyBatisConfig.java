package proyecto2.mtsolutions.configuration.mybatis;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(value = "proyecto2.mtsolutions.dao", sqlSessionFactoryRef = "sqlSessionConfig")
public class MyBatisConfig {
    @Value("${datasource.postgres.mtsolutions.url}")
    private String url;

    @Value("${datasource.postgres.mtsolutions.user}")
    private String user;

    @Value("${datasource.postgres.mtsolutions.pass}")
    private String pass;

    @Value("${mybatis.mapper.location}")
    private Resource[] mapers;

    @Bean(name = "dataSourceInterno")
    public DataSource dataSourceInterno() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(user);
        dataSourceBuilder.password(pass);
        return dataSourceBuilder.build();
    }

    @Bean(name = "sqlSessionConfig")
    public SqlSessionFactory sqlSessionFactoryInterno() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSourceInterno());
        factoryBean.setMapperLocations( mapers );
        factoryBean.setTypeAliasesPackage("proyecto2.mtsolutions.dto");
        return factoryBean.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSourceInterno());
    }



}
