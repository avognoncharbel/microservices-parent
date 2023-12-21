package com.kinikini.orderservice.controller;

import com.kinikini.orderservice.dto.OrderRequest;
import com.kinikini.orderservice.dto.ProductResponse;
import com.kinikini.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    WebClient webclient = WebClient.builder()
            .baseUrl("http://localhost:8080/api/product")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build();

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> placeOrder(@RequestBody OrderRequest orderRequest){
       orderService.placeOrder(orderRequest);
//        getProductResponse();
        return ResponseEntity.ok("Order successfully placed");

    }


//    private void getProductResponse(){
//        Mono<ProductResponse > productResponseFlux = webclient.get().uri("/api/product").retrieve().bodyToMono(ProductResponse.class);
//        System.out.println("********************************************" +productResponseFlux);
//    }
}
