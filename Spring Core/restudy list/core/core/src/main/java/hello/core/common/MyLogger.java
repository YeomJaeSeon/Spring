package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Scope(value = "request")
public class MyLogger {
    private String requestURL;
    private String uuid;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message){
        System.out.println("["+uuid+"]"+"["+requestURL+"] " + message);
    }

    @PostConstruct
    public void init(){
        System.out.println(uuid + " request bean create " + this);
    }

    @PreDestroy
    public void destroy(){
        System.out.println(uuid + " request bean destroy " + this);
    }
}
