package py.una.pol.got.configuration;

import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@ComponentScan({ "py.una.pol.got.configuration" })
@PropertySources({ @PropertySource(value = "classpath:properties/appconfig.properties") })
@EnableAsync
public class AppConfiguration {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        PropertySourcesPlaceholderConfigurer psc = new PropertySourcesPlaceholderConfigurer();
        return psc;
    }
}
