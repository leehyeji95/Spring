package hello.core;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HelloLombok {

    private String name;
    private int age;

    // Lombok 의 기능 :
//    Getter, Setter 생성자 등 자동으로 만들어준다.

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("sdfdf");
        helloLombok.setAge(29);

        System.out.println("name = " + helloLombok.getName() + ", age = "+helloLombok.getAge());
        System.out.println("helloLombok = " + helloLombok);

    }
}
