package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.web.reactive.context.AnnotationConfigReactiveWebApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext appCon = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    void findAllBean() {
        String[] beanArray = appCon.getBeanDefinitionNames();
        // 모든 빈 출력
//        for (String str : beanArray) {
//            System.out.println(str);
//        }

        // 역할에 따른 빈 출력 (Appl / Infra)
        for (String str : beanArray) {
            BeanDefinition beanDef = appCon.getBeanDefinition(str);

            if(beanDef.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = appCon.getBean(str);
                System.out.println("name = " + str + " / object = " + bean);
            }
        }
    }
}
