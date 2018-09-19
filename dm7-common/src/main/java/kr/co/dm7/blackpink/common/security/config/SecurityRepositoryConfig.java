package kr.co.dm7.blackpink.common.security.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import static java.util.Collections.singletonMap;

/**
 * 일단 h2 기반의 jpa 로 기본 동작하게 했으며 이는 추후 DB 가 결정되었을 때 Dialect 을 다시 세팅하여 처리하도록 하겠다
 * 해당 config 에서는 Jpa 사용에 대한 선언과 와 Repository 및 대상 Entity 에 대한 package scan 을 하도록 설정해 뒀다
 * 혹시 multiple Datasource 를 사용하게 된다면 그때 이 부분은 다시 한번 손 보는 것으로 해야 할 것 같다.
 * 아니면 sub module 이 아닌 해당 프로젝트를 실제 사용하는 쪽으로 설정을 옮겨야 할지도 모르겠다
 *
 * @author doubleseven
 * @version 1.0
 */
//@Configuration
//@PropertySource("classpath:persistence-generic-entity.properties")
//@EnableJpaRepositories(
//        basePackages = "kr.co.dm7.blackpink.common.security.repository",
//        entityManagerFactoryRef = "heroesWriteEntityManager",
//        transactionManagerRef = "heroesWriteTransactionManager"
//)
// @EntityScan(basePackages = "kr.co.dm7.blackpink.common.security.domain")
//@Configuration
//@EnableTransactionManagement
//@PropertySource({"classpath:persistence-h2.properties"})
//@EnableJpaRepositories(basePackages = "kr.co.dm7.blackpink.common.security.repository")

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"kr.co.dm7.blackpink.common.security.repository"})
@EntityScan(basePackages = "kr.co.dm7.blackpink.common.security.domain")
public class SecurityRepositoryConfig {

//    @Primary
//    @Bean(name = "dataSource")
//    @ConfigurationProperties(prefix = "oauthuser.datasource")
//    public DataSource dataSource() {
//        return DataSourceBuilder.create().build();
//    }
//
//    @Primary
//    @Bean(name = "entityManagerFactory")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            EntityManagerFactoryBuilder builder, @Qualifier("dataSource") DataSource dataSource) {
//        return builder
//                .dataSource(dataSource)
//                .packages("kr.co.dm7.blackpink.common.security.domain")
//                .persistenceUnit("oauthuser")
//                .properties(singletonMap("hibernate.hbm2ddl.auto", "create-drop"))
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "transactionManager")
//    public PlatformTransactionManager transactionManager(@Qualifier("entityManagerFactory") EntityManagerFactory entityManagerFactory) {
//        return new JpaTransactionManager(entityManagerFactory);
//    }

    /**
     * 패스워드 및 기타 암호화가 필요한 사항들에 대해서 인코딩을 하기 위한 Bean 이다
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
