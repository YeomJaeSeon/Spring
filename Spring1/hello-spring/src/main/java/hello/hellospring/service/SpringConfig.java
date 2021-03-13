package hello.hellospring.service;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

//Spring이 뜰때 이 @Configuration 어노테이션읽고
//이거는 Spring bean에 등록하란뜻이네? 라고 스프링이 인식을하고
@Configuration
public class SpringConfig {

    @PersistenceContext // 이건있어도되고 없어도됨.
   private EntityManager em;

   @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
//        생성자 인자에 memberRepository를 넣어준다. - memberService에서 member
//        Repository 의존성 주입받으므로.
//        이렇게되면 Spring 빈에등록되어잇는 memberRepository를 memberService에 넣어준다.
//        @Autowired와같이 의존성 관계를 설정해줌(DI)
    }
    @Bean
    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
        return new JpaMemberRepository(em);
    }
}

// 어떤과정으로 스프링 빈에 등록되냐면
// @Configuration스프링이보면 스프링빈에 등록하란의미로 @Bean인 membersErvice와
// MemberRepository를 스프링빈에 등록하고 MembersErvice 생성자 인자에 memberRepository()
// 를 보고 memberSErvice에 memberRepository를 DI한다. (@Autowired)와 똑같즹?

//@Controller는 어쩔수없음. 스프링이 어짜피 관리하는 것이므로 따로 자바코드로 스프링빈 등록안해도
//@Controller 어노테이션으로 올라가고, 컴포넌트 스캔 이므로 @Autowired로 의존관계 설정한다.
//@Controller 는 @Autowired할때 @Configuration에서 @Bean어노테이션 있는 MemberService
//(Spring컨테이너에 등록된 스프링빈)을 넣어준다. 쉽당.

//과거엔 자바코드로 설정하지않고 XML이라는 문서로 설정함.. 스프링빈을
//지금은 잘 사용하지않아. 자바코드로 스프링빈을 설정함(등록함)

//DI방식에는 필드주입, setter주입, 생성자주입 세가지가있당. 우리가한것들
/* 생성자 주입 - best!!!
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
 */
/* 필드주입
    @Autowired private MemberService memberService; - 별로안좋음
    왱? 뭔가 바꿀수있는 방법이없음. 중간에 뭔가 바꿀수잇는방법이 없다.
 */

/* setter주입

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    생성자는 생성된다음 setter에 의해서 의존성이 주입됨.
    setter주입의 단점은 public하게 노출이된다는점. 한번 설정되면 바뀔이유가없는데
    public하게 노출될 이유도없다.중간에 잘못바꿀수있땅.(setter로)

    한번 setting이일어나면 이러한 의존관계나 service를 바꿀필요가없다.

    의존관계가 동적으로 변하는 경우는 아애없다. 생성자 주입이 좋다 그러므로.
 */

// 실무에서 주로 정형화된 컨트롤러, 서비스 리파지토리같은 코드는 컴포넌트 스캔방식으로 스프링빈
// 등록한당. 그러나 정형화되지않거나 상황에 따라 구현클래스를 변경해야 하면 자바 코드 설정을 통해
// 스프링빈으로등록한다. -> 우리는 memberRepository를 설계할때 데이터 저장소가 선정되지 않았다는
// 시나리오가있으므로 메모리로 일단만들고 교체하기로했다.
// 기존에 운영중인 코드를 하나도 손대지않고 바꿔치기할수있음. 즉, 구현체를 바꾸지않고
// 직접 자바 코드로 스프링빈 등록하는 방법(설정파일로)으로 바꿀수있다.
// 설정파일만 변경해서 딱 변경할수있따..!

// 스프링빈에 등록되어있지 않으면 @Autowired적용은 당연히안된다. 이건 당연한거잖아 ;;
// 스프링 컨테이너에 올라간 녀석들만 @Autowired기능이 적용된다. 이건 자명한거임