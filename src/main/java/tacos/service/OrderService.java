package tacos.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tacos.dto.UserDto;
import tacos.entity.TacoOrder;
import tacos.entity.User;
import tacos.repository.OrderRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;

    public TacoOrder findById(Long id){
        return orderRepository.findById(id).get();
    };

    public Iterable<TacoOrder> findAll(){
       return orderRepository.findAll();
    }

    public Page<TacoOrder> findOrdersForUser (UserDto user, Pageable pageable){
        return orderRepository.findByUserOrderByPlacedAtDesc(user, pageable);
    }

    @Transactional
    public TacoOrder create(TacoOrder order){
        return orderRepository.save(order);
    }

    @Transactional
    public TacoOrder create(TacoOrder order, Long orderId){
        return Optional.of(order)
                .map(tacoOrder -> {
                    tacoOrder.setId(orderId);
                    return tacoOrder;
                })
                .map(orderRepository::save)
                .orElseThrow();
    }

    @Transactional
    public TacoOrder create(TacoOrder order, User user){
        return Optional.of(order)
                .map(tacoOrder -> {
                    tacoOrder.setUser(user);
                    return tacoOrder;
                })
                .map(orderRepository::save)
                .orElseThrow();
    }
    @Transactional
    public TacoOrder update(TacoOrder patch, Long id){
        TacoOrder order = orderRepository.findById(id).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return orderRepository.save(order);
    }

    public void deleteById(Long id) {
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