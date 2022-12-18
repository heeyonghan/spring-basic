package hello.core.scan.filter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

public class FilterConfigTest {
    @Test
    void filterScan() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(FilterConfig.class);
        BeanB beanB = ac.getBean("beanB", BeanB.class);

        Assertions.assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanA", BeanA.class));

    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = IncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = ExcludeComponent.class)
    )
    static class FilterConfig {

    }

}
