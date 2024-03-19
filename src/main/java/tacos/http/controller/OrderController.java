package tacos.http.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.entity.TacoOrder;
import tacos.entity.User;
import tacos.service.OrderService;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/current")
    public String orderForm(@ModelAttribute TacoOrder order,
                            @AuthenticationPrincipal User user) {
        orderService.autoFillOrderWithUserData(order, user);
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Validated TacoOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        var tacoOrder = orderService.create(order, user);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
