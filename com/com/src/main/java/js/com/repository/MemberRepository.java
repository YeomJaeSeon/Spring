package js.com.repository;

public class MemberRepository {
    private static MemberRepository instance = new MemberRepository();

    private MemberRepository(){}
    public static MemberRepository getInstance(){
        return instance;
    }
}
