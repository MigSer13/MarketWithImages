package ru.geekbrains.summer.market.services;

import com.paypal.orders.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.summer.market.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PayPalService {
    private final OrderService orderService;

    @Transactional
    public OrderRequest createOrderRequest(Long orderId) {
        ru.geekbrains.summer.market.model.Order order = orderService.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found"));


        OrderRequest orderRequest = new OrderRequest();
        orderRequest.checkoutPaymentIntent("CAPTURE");

        ApplicationContext applicationContext = new ApplicationContext()
                .brandName("Summer Market")
                .landingPage("BILLING")
                .shippingPreference("SET_PROVIDED_ADDRESS");
        orderRequest.applicationContext(applicationContext);

        List<PurchaseUnitRequest> purchaseUnitRequests = new ArrayList<>();
        PurchaseUnitRequest purchaseUnitRequest = new PurchaseUnitRequest()
                .referenceId(orderId.toString())
                .description("Summer Order")
                .amountWithBreakdown(new AmountWithBreakdown().currencyCode("RUB").value(order.getPrice().toString())
                        .amountBreakdown(new AmountBreakdown().itemTotal(new Money().currencyCode("RUB").value(order.getPrice().toString()))))
                .items(order.getItems().stream()
                        .map(orderItem -> new Item()
                                .name(orderItem.getProduct().getTitle())
                                .unitAmount(new Money().currencyCode("RUB").value(orderItem.getPrice().toString()))
                                .quantity(String.valueOf(orderItem.getQuantity())))
                        .collect(Collectors.toList()))
//               .shippingDetail(new ShippingDetail().name(new Name().fullName(order.getUser().getUsername()))
                .shippingDetail(new ShippingDetail()
                        .name(new Name().fullName(order.getAddress().getFirstName() + " " + order.getAddress().getLastName()))
                        .addressPortable(new AddressPortable()
                                        .addressLine1(order.getAddress().getAddress1())
                                        .addressLine2(order.getAddress().getAddress2())
                                        .adminArea1(order.getAddress().getCity())
                                        .adminArea2(order.getAddress().getCountry())

                        ));
//                        .addressPortable(new AddressPortable().addressLine1("123 Townsend St").addressLine2("Floor 6")
//                                .adminArea2("San Francisco").adminArea1("CA").postalCode("94107").countryCode("US")));
        purchaseUnitRequests.add(purchaseUnitRequest);
        orderRequest.purchaseUnits(purchaseUnitRequests);
        return orderRequest;
    }
}

