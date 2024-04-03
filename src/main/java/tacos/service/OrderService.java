package tacos.service;

import tacos.entity.TacoOrder;
import tacos.entity.User;

import java.util.Optional;


public interface OrderService {

    Iterable<TacoOrder> findAllOrders();

    Optional<TacoOrder> findOrderById(Long orderId);

    TacoOrder createOrder(TacoOrder order);

    TacoOrder createOrder(TacoOrder order, User user);

    void updateOrder(Long orderId, TacoOrder order);

    void partialUpdateOrder(Long orderId, TacoOrder order);

    void deleteOrder(Long orderId);

    void autoFillOrderWithUserData(TacoOrder order, User user);
}


