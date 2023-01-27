package by.home.jarward.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableScheduling
@PropertySource({"classpath:database.properties", "classpath:mail_sender.properties"})
@ComponentScan(basePackages = {"by.home.jarward.web"})
@EnableJpaRepositories(basePackages = "by.home.jarward.jar.repository")
public class AppContext {

    @Autowired
    public Environment environment;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        entityManagerFactory.setDataSource(dataSource());
        entityManagerFactory.setPersistenceUnitName("jpa-unit");
        entityManagerFactory.setPackagesToScan("by.home.jarward.jar.entity");
        entityManagerFactory.setJpaVendorAdapter(vendorAdapter);
        entityManagerFactory.setJpaProperties(hibernateProperties());

        return entityManagerFactory;
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
        properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.jdbc.batch_size", environment.getRequiredProperty("hibernate.jdbc.batch_size"));
        properties.put("spring.jpa.properties.hibernate.generate_statistics", environment.getRequiredProperty("hibernate.generate_statistics"));

        return properties;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(entityManagerFactory().getObject());

        return jpaTransactionManager;
    }

    @Bean
    public JavaMailSenderImpl getJavaMailSenderImpl() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setPort(Integer.parseInt(environment.getRequiredProperty("mail.port")));
        javaMailSender.setUsername(environment.getRequiredProperty("mail.username"));
        javaMailSender.setPassword(environment.getRequiredProperty("mail.password"));
        javaMailSender.setJavaMailProperties(getJavaMailSenderProperties());

        return javaMailSender;
    }

    private Properties getJavaMailSenderProperties() {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", environment.getRequiredProperty("mail.transport.protocol"));
        properties.put("mail.smtp.auth", environment.getRequiredProperty("mail.smtp.auth"));
        properties.put("mail.smtp.host", environment.getRequiredProperty("mail.smtp.host"));
        properties.put("mail.smtp.starttls.enable", environment.getRequiredProperty("mail.smtp.starttls.enable"));
        properties.put("mail.debug", environment.getRequiredProperty("mail.debug"));
        properties.put("mail.smtp.starttls.required", environment.getRequiredProperty("mail.smtp.starttls.required"));
        properties.put("mail.smtp.socketFactory.class", environment.getRequiredProperty("mail.smtp.socketFactory.class"));

        return properties;
    }
}
