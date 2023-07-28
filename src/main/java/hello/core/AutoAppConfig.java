package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

@Configuration
@ComponentScan(
//        basePackages = "hello.core.member",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
// @Component 가 붙은 class 를 찾아서 자동으로 Spring Bean 으로 등록해준다.
// exclude 는 자동으로 bean 등록 시에 뺄 class 를 지정해준다.
// @Configuration AppConfig 는 자동으로 등록되면 안되기 때문에 filter 설정
public class AutoAppConfig {

//    @Bean(name="memoryMemberRepository")    // 수동 빈 등록 우선권(수동 빈이 자동 빈을 오버라이딩 한다.)
//    public MemoryMemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }

}
