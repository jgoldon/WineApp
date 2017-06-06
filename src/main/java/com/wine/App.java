package com.wine;
import com.wine.filter.AuthenticationFilter;
import com.wine.model.TokenDao;
import com.wine.model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class App 
{
    @Bean
    @Autowired
    public FilterRegistrationBean auditFilterRegistration(TokenDao tokenDao) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new AuthenticationFilter(tokenDao));
        filterRegistrationBean.addUrlPatterns("/api/v1/wines", "/api/v1/wines/*", "/api/v1/reviews");
        return filterRegistrationBean;
    }

/*    @Bean
    public AuthenticationFilter shallowEtagHeaderFilter() {
        return new AuthenticationFilter();
    }*/

    public static void main( String[] args )
    {
        SpringApplication.run(App.class, args);
    }
}
