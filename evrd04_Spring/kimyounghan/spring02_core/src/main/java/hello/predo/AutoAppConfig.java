package hello.predo;

import hello.predo.member.MemberRepository;
import hello.predo.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;


// section 6-1 컴포넌트스캔과 의존관계 자동 주입 시작하기
@Configuration
@ComponentScan(
        basePackages = "hello.predo",
//      basePackages = {"hello.core", "hello.service"}
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
public class AutoAppConfig {

    @Bean(name = "memoryMemberRepository")
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

}
