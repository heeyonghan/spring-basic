package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class OrderApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        // Spring Container
        ApplicationContext appCon = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = appCon.getBean("memberService", MemberService.class);
        OrderService orderService = appCon.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "AABBCC", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "Item1", 20000);

        System.out.println("Order = " + order);
        System.out.println("Order = " + order.calculatePrice());

    }
}
