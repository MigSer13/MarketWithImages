package ru.geekbrains.summer.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.summer.market.dto.OrderDto;
import ru.geekbrains.summer.market.exceptions.InvalidInputDataException;
import ru.geekbrains.summer.market.exceptions.ResourceNotFoundException;
import ru.geekbrains.summer.market.model.Order;
import ru.geekbrains.summer.market.model.User;
import ru.geekbrains.summer.market.services.OrderService;
import ru.geekbrains.summer.market.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    //public void createOrder(Principal principal, @RequestParam String address, @RequestParam String phone) {
    public void createOrder(Principal principal, @RequestBody Map<String, String> data) {
        List<String> errors = new ArrayList<>();
        if (data.get("firstName").isBlank()) {
            errors.add("Field 'firstName' cannot be null");
        }
        if (data.get("lastName").isBlank()) {
            errors.add("Field 'lastName' cannot be null");
        }
        if (data.get("address1").isBlank()) {
            errors.add("Field 'address1' cannot be null");
        }
        if (data.get("city").isBlank()) {
            errors.add("Field 'city' cannot be null");
        }
        if (data.get("country").isBlank()) {
            errors.add("Field 'country' cannot be null");
        }
        if (data.get("phone").isBlank()) {
            errors.add("Field 'phone' cannot be null");
        }
        if (!errors.isEmpty()) {
            throw new InvalidInputDataException(errors);
        }
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order. User not found"));
        orderService.createOrder(user, data);
    }

    @GetMapping
    public List<OrderDto> getAllOrders(Principal principal) {
        return orderService.findAllDtosByUsername(principal.getName());
    }
}
