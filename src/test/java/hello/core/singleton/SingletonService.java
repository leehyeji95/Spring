package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();    // 자기 자신을 내부에 static 영역에 생성

    public static SingletonService getInstance() {
        // JAVA 실행 시 (로딩 시) 자바 인스턴스 객체 생성해둔다.
        // getInstance 메서드 호출 시 항상 같은 인스턴스만 반환
        return instance;
    }

    // private 생성자
    private SingletonService() {
        // 딱 1개의 객체 인스턴스만 존재하도록 하기 위해서 생성자를 private 으로 만들어서
        // 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되지 못하도록 막는다.
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    // 클래스마다 이런 설정을 해줘야하나? -> 스프링 컨테이너가 객체를 모두 싱글톤으로 만들어준다.!!
    // 있는 객체 재활용 -> 성능향상

}
