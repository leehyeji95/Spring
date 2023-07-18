package hello.core.singleton;

public class StatefulService {

    private int price;  // 상태를 유지하는 필드(가격)

    public void order(String name, int price) {
        System.out.println("name = "+name+", price = "+price);
        this.price = price; // 여기가 문제!

        // 지역변수 사용하는 것으로 변경하기
        // return price;
    }

    public int getPrice() {
        return price;
    }
}
