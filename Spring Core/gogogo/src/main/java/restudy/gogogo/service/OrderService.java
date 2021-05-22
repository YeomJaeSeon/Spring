package restudy.gogogo.service;

import restudy.gogogo.domain.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
