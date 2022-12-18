package hello.core.autowired;

import hello.core.member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

public class AutoWiredTest {

    @Test
    void autoWiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(OptionConfig.class);

    }

    static class OptionConfig {
        @Autowired(required = false)
        public void setNoBean1(Member mem1) {
            System.out.println("mem1 = " + mem1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member mem2) {
            System.out.println("mem2 = " + mem2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> mem3) {
            System.out.println("mem3 = " + mem3);
        }
    }
}
