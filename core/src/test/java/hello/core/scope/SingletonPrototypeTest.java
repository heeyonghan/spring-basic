package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class SingletonPrototypeTest {

    @Test
    void providerTest() {
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(SingletonBean.class, PrototypeBean.class);

        SingletonBean Bean1 = ac.getBean(SingletonBean.class);
        int cnt1 = Bean1.logic();
        System.out.println("cnt1 = " + cnt1);
        Assertions.assertThat(cnt1).isEqualTo(1);

        SingletonBean Bean2 = ac.getBean(SingletonBean.class);
        int cnt2 = Bean2.logic();
        System.out.println("cnt2 = " + cnt2);
        Assertions.assertThat(cnt2).isEqualTo(1);
    }


    static class SingletonBean {
//        @Autowired
//        private ApplicationContext ac;

        @Autowired // ObjectProvider
        private ObjectProvider<PrototypeBean> prototypeProvider;

        public int logic() {
            PrototypeBean prototypeBean = prototypeProvider.getObject();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean {
        private int count = 0;

        public void addCount() {
            count ++;
        }

        public int getCount() {
            return count;
        }

        @PostConstruct
        public void init() {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
