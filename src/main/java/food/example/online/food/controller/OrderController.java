package food.example.online.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import food.example.online.food.dto.OrderRequest;
import food.example.online.food.dto.OrderResponse;
import food.example.online.food.dto.PaymentRespopnse;
import food.example.online.food.entity.Order;
import food.example.online.food.entity.User;
import food.example.online.food.service.OrderService;
import food.example.online.food.service.PaymentService;
import food.example.online.food.service.UserService;

@RestController
@RequestMapping("/api")
public class OrderController {

	private UserService userService;
	private OrderService orderService;
	private PaymentService paymentService;
	
	@Autowired
	public OrderController(UserService userService, OrderService orderService,
			PaymentService paymentService) {
		this.userService = userService;
		this.orderService = orderService;
		this.paymentService = paymentService;
	}
	
	@PostMapping("/order")
    public ResponseEntity<PaymentRespopnse> createOrder(
            @RequestBody OrderRequest req,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwt(jwt);
        OrderResponse response = orderService.createOrder(req, user);

        PaymentRespopnse res = paymentService.createPaymentLink(response);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

	@GetMapping("/order/user")
	public ResponseEntity<List<OrderResponse>> getOrderHistory(@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwt(jwt);
		List<OrderResponse> order = orderService.getUserOrder(user.getId());
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	

}
