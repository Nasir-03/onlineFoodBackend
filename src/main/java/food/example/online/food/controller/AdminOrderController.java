package food.example.online.food.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import food.example.online.food.dto.OrderResponse;
import food.example.online.food.entity.Order;
import food.example.online.food.entity.User;
import food.example.online.food.service.OrderService;
import food.example.online.food.service.UserService;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

	private UserService userService;
	private OrderService orderService;
	
	public AdminOrderController(UserService userService, OrderService orderService) {
		this.userService = userService;
		this.orderService = orderService;
	}
	
	@GetMapping("/order/resturant/{id}")
	public ResponseEntity<List<OrderResponse>> getOrderHistory(
			@PathVariable Long id,
			@RequestParam(required = false)String order_status,
			@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwt(jwt);
		List<OrderResponse> order = orderService.getResturantOrder(id, order_status);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
	
	@PutMapping("/order/{orderId}/{orderStatus}")
	public ResponseEntity<Order> updateStatus(
			@PathVariable Long orderId,
			@PathVariable String orderStatus,
			@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwt(jwt);
		Order order = orderService.updateOrder(orderId, orderStatus);
		
		return new ResponseEntity<>(order,HttpStatus.OK);
	}
}
