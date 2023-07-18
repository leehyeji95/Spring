package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class))
//@Component 가 붙은 class 를 찾아서 자동으로 Spring Bean 으로 등록해준다.
// exclude 는 자동으로 bean 등록 시에 뺄 class 를 지정해준다.
// @Configuration AppConfig 는 자동으로 등록되면 안되기 때문에 filter 설정
public class AutoAppConfig {
}
