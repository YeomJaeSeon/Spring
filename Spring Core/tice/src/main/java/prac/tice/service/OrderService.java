package prac.tice.service;

import prac.tice.domain.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
}
