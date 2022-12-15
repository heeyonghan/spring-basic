package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

//    GenericXmlApplicationContext ac = new GenericXmlApplicationContext(appConfig.xml);

    @Test
    void findBeanDefinition() {
        String[] beanDef = ac.getBeanDefinitionNames();
        for (String str : beanDef) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(str);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("BeanDefinitionName = " + str + " / BeanDefinition = " + beanDefinition);
            }
        }
    }
}
