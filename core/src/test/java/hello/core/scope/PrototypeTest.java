package hello.core.scope;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

public class PrototypeTest {

    @Test
    public void prototypeBeanFind() {
        // AnnotationConfigApplicationContext 으로 선언할 경우, close() 메소드 사용 가능
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        // Singleton 으로 Bean 생성한 경우 getBean의 결과가 같음.
        System.out.println("Prototype Bean 1 생성");
        PrototypeBean pb1 = ac.getBean(PrototypeBean.class);
        System.out.println("Prototype Bean 2 생성");
        PrototypeBean pb2 = ac.getBean(PrototypeBean.class);
        System.out.println("pb1 = " + pb1);
        System.out.println("pb2 = " + pb2);
        Assertions.assertThat(pb1).isNotSameAs(pb2);

        // Prototype은 스프링 컨테이너의 관리 대상이 아니므로, close() 메소드 수행 안됨.
        ac.close();

        // 생성된 객체의 destroy() 메소드를 직접 호출하여 종료해야 함.
        pb1.destroy();
        pb2.destroy();

    }

    @Scope("prototype")
    static class PrototypeBean {
        @PostConstruct
        public void init() {
            System.out.println("init() start");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("destroy() start");
        }
    }
}
