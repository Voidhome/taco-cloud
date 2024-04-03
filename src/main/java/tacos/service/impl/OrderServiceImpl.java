package tacos.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tacos.entity.TacoOrder;
import tacos.entity.User;
import tacos.repository.OrderRepository;
import tacos.service.OrderService;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public Optional<TacoOrder> findOrderById(Long orderId){
        return orderRepository.findById(orderId);
    }

    public Iterable<TacoOrder> findAllOrders(){
        return orderRepository.findAll();
    }

    @Transactional
    public TacoOrder createOrder(TacoOrder order){
        return orderRepository.save(order);
    }

    @Transactional
    public TacoOrder createOrder(TacoOrder order, User user){
        return Optional.of(order)
                .map(tacoOrder -> {
                    tacoOrder.setUser(user);
                    return tacoOrder;
                })
                .map(orderRepository::save)
                .orElseThrow();
    }

    @Transactional
    public void updateOrder(Long orderId, TacoOrder tacoOrder){
        orderRepository.findById(orderId)
                .map(order -> {
                    order.setDeliveryName(tacoOrder.getDeliveryName());
                    order.setDeliveryStreet(tacoOrder.getDeliveryStreet());
                    order.setDeliveryCity(tacoOrder.getDeliveryCity());
                    order.setDeliveryState(tacoOrder.getDeliveryState());
                    order.setDeliveryZip(tacoOrder.getDeliveryZip());
                    order.setCcNumber(tacoOrder.getCcNumber());
                    order.setCcExpiration(tacoOrder.getCcExpiration());
                    order.setCcCVV(tacoOrder.getCcCVV());
                    return orderRepository.save(order);
                })
                .orElseThrow(NoSuchElementException::new);
    }

    @Transactional
    public void partialUpdateOrder(Long orderId, TacoOrder order){
        TacoOrder tacoOrder = orderRepository.findById(orderId).get();
        if (order.getDeliveryName() != null) {
            tacoOrder.setDeliveryName(order.getDeliveryName());
        }
        if (order.getDeliveryStreet() != null) {
            tacoOrder.setDeliveryStreet(order.getDeliveryStreet());
        }
        if (order.getDeliveryCity() != null) {
            tacoOrder.setDeliveryCity(order.getDeliveryCity());
        }
        if (order.getDeliveryState() != null) {
            tacoOrder.setDeliveryState(order.getDeliveryState());
        }
        if (order.getDeliveryZip() != null) {
            tacoOrder.setDeliveryZip(order.getDeliveryZip());
        }
        if (order.getCcNumber() != null) {
            tacoOrder.setCcNumber(order.getCcNumber());
        }
        if (order.getCcExpiration() != null) {
            tacoOrder.setCcExpiration(order.getCcExpiration());
        }
        if (order.getCcCVV() != null) {
            tacoOrder.setCcCVV(order.getCcCVV());
        }
        orderRepository.save(tacoOrder);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public void autoFillOrderWithUserData(TacoOrder order, User user) {
        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullname());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }
        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(user.getCity());
        }
        if (order.getDeliveryState() == null) {
            order.setDeliveryState(user.getState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }
    }
}

