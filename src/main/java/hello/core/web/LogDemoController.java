package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

//    @RequiredArgsConstructor -> 생성자 Autowired 생략 가능

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    // ** 스프링 뜰때 Request 없기 때문에 Provider 사용해서 스코프 조회하기 **
//    private final ObjectProvider<MyLogger> myLoggerProvider;

    @RequestMapping("log-demo")
    @ResponseBody // View 화면없이 문자 바로 반환(문자로 바로 응답)
    public String logDemo(HttpServletRequest request) {
        System.out.println("myLogger = "+myLogger.getClass());

        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller Test");
        logDemoService.logic("testId");
        return "OK";
    }

}
