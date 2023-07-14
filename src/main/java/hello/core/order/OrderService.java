package hello.core.order;

public interface OrderService {
    // 클라언트는 주문 생성 시, (회원id/상품명/상품가격) 넘겨야하고 return 주문 결과
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
