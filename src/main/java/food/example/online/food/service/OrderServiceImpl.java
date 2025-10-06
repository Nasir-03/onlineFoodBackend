package food.example.online.food.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import food.example.online.food.dto.AddressResponse;
import food.example.online.food.dto.CustomerResponse;
import food.example.online.food.dto.OrderItemResponse;
import food.example.online.food.dto.OrderRequest;
import food.example.online.food.dto.OrderResponse;
import food.example.online.food.entity.Address;
import food.example.online.food.entity.Cart;
import food.example.online.food.entity.CartItems;
import food.example.online.food.entity.Order;
import food.example.online.food.entity.OrderItems;
import food.example.online.food.entity.Resturant;
import food.example.online.food.entity.User;
import food.example.online.food.repository.AdressRepository;
import food.example.online.food.repository.OrderItemRepository;
import food.example.online.food.repository.OrderRepository;
import food.example.online.food.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private OrderRepository orderRepository;
	private OrderItemRepository orderItemRepository;
	private AdressRepository adressRepository;
	private UserRepository userRepository;
	private ResturantService resturantService;
	private CartService cartService;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, OrderItemRepository orderItemRepository,
			AdressRepository adressRepository, UserRepository userRepository, ResturantService resturantService,
			CartService cartService) {
		this.orderRepository = orderRepository;
		this.orderItemRepository = orderItemRepository;
		this.adressRepository = adressRepository;
		this.userRepository = userRepository;
		this.resturantService = resturantService;
		this.cartService = cartService;
	}

//	@Override
//	public Order createOrder(OrderRequest order, User user)throws Exception {
//		Address shipAddress = order.getDeliveryAddress();
//		Address savedAddress = adressRepository.save(shipAddress);
//		
//		if (!user.getAddresses().contains(savedAddress)) {
//			user.getAddresses().add(savedAddress);
//			userRepository.save(user);
//		}
//		
//		Resturant resturant = resturantService.findResturantById(order.getResturantId());
//		
//		Order createOrder = new Order();
//		createOrder.setCustomer(user);
//		createOrder.setCreateAt(new Date());
//		createOrder.setDeliveryAddress(shipAddress);
//		createOrder.setOrderStatus("PENDING");
//		createOrder.setResturant(resturant);
//		
//		Cart cart = cartService.findCartByUserId(user.getId());
//		
//		List<OrderItems> orderItems = new ArrayList<>();
//		
////		for (CartItems items : cart.getListItems()) {
////			OrderItems orderItem = new OrderItems();
////			orderItem.setFood(items.getFood());
////			orderItem.setIngredients(items.getIngredients());
////			orderItem.setQuantity(items.getQuantity());
////			orderItem.setTotalPrice(items.getTotalPrice());
////		
////		    OrderItems savedOrderItems = orderItemRepository.save(orderItem);
////		    orderItems.add(savedOrderItems);
////		}
//		
//		for (CartItems items : cart.getListItems()) {
//		    OrderItems orderItem = new OrderItems();
//		    orderItem.setFood(items.getFood());
//		    orderItem.setIngredients(new ArrayList<>(items.getIngredients())); // ✅ copy list
//		    orderItem.setQuantity(items.getQuantity());
//		    orderItem.setTotalPrice(items.getTotalPrice());
//
//		    OrderItems savedOrderItems = orderItemRepository.save(orderItem);
//		    orderItems.add(savedOrderItems);
//		}
//
//		
//		
//		createOrder.setOrderItems(orderItems);
//		createOrder.setTotalPrice(cart.getTotal());
//		
//		Order savedOrder = orderRepository.save(createOrder);
//		resturant.getOrders().add(savedOrder);
//		return createOrder;
//	}

	@Override
	public OrderResponse createOrder(OrderRequest order, User user) throws Exception {
		Address shipAddress = order.getDeliveryAddress();
		Address savedAddress = adressRepository.save(shipAddress);

		if (!user.getAddresses().contains(savedAddress)) {
			user.getAddresses().add(savedAddress);
			userRepository.save(user);
		}

		Resturant resturant = resturantService.findResturantById(order.getResturantId());

		Order createOrder = new Order();
		createOrder.setCustomer(user);
		createOrder.setCreateAt(new Date());
		createOrder.setDeliveryAddress(savedAddress);
		createOrder.setOrderStatus("PENDING");
		createOrder.setResturant(resturant);

		Cart cart = cartService.findCartByUserId(user.getId());

		List<OrderItems> orderItems = new ArrayList<>();

		for (CartItems items : cart.getListItems()) {
			OrderItems orderItem = new OrderItems();
			orderItem.setFood(items.getFood());
			orderItem.setIngredients(new ArrayList<>(items.getIngredients())); // copy
			orderItem.setQuantity(items.getQuantity());
			orderItem.setTotalPrice(items.getTotalPrice());

			orderItem.setOrder(createOrder);
			orderItems.add(orderItem);
//	            OrderItems savedOrderItems = orderItemRepository.save(orderItem);
//	            orderItems.add(savedOrderItems);
		}
		
		

		createOrder.setOrderItems(orderItems);
		createOrder.setTotalPrice(cart.getTotal());
		createOrder.setTotalItem(orderItems.size());

		Order savedOrder = orderRepository.save(createOrder);
		resturant.getOrders().add(savedOrder);

		return mapToResponse(savedOrder);
	}

	// Map to response for create order
	private OrderResponse mapToResponse(Order order) {
		OrderResponse response = new OrderResponse();
		response.setId(order.getId());
		response.setOrderStatus(order.getOrderStatus());
		response.setCreateAt(order.getCreateAt());
		response.setTotalPrice(order.getTotalPrice());
		response.setTotalItem(order.getTotalItem());

		AddressResponse addressResponse = new AddressResponse();
		addressResponse.setId(order.getDeliveryAddress().getId());
		addressResponse.setCity(order.getDeliveryAddress().getCity());
		addressResponse.setStreet(order.getDeliveryAddress().getStreet());
		addressResponse.setPinCode(order.getDeliveryAddress().getPinCode());
		addressResponse.setState(order.getDeliveryAddress().getState());
		response.setDeliveryAddress(addressResponse);

		List<OrderItemResponse> itemResponses = order.getOrderItems().stream().map(item -> {
			OrderItemResponse ir = new OrderItemResponse();
			ir.setId(item.getId());
			ir.setFoodName(item.getFood().getName());
			ir.setQuantity(item.getQuantity());
			ir.setTotalPrice(item.getTotalPrice());
			ir.setIngredients(item.getIngredients());
			return ir;
		}).toList();

		response.setOrderItems(itemResponses);
		
		User customerUser = new User();
		customerUser.setEmail(order.getCustomer().getEmail());
		customerUser.setFullName(order.getCustomer().getFullName());

		return response;
	}

	@Override
	public Order updateOrder(Long orderId, String orderStatus) throws Exception {
		Order order = findOrderById(orderId);

		if (orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") || orderStatus.equals("COMPLETED")
				|| orderStatus.equals("PENDING")) {
			order.setOrderStatus(orderStatus);
			return orderRepository.save(order);
		}
		throw new Exception("please select a valid status");
	}

	@Override
	public void cancelOrder(Long orderId) throws Exception {
		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
	}

//	@Override
//	public List<Order> getUserOrder(Long userId) throws Exception {
//		return orderRepository.findByCustomerId(userId);
//	}

	@Override
	public List<OrderResponse> getUserOrder(Long userId) throws Exception {
		List<Order> orders = orderRepository.findByCustomerId(userId);
		return orders.stream().map(this::convertToResponse).toList();
	}

	// This is just convert entity to response
	private OrderResponse convertToResponse(Order order) {
	    // Map delivery address
	    AddressResponse addr = null;
	    if (order.getDeliveryAddress() != null) {
	        addr = new AddressResponse(
	            order.getDeliveryAddress().getId(),
	            order.getDeliveryAddress().getStreet(),
	            order.getDeliveryAddress().getCity(),
	            order.getDeliveryAddress().getPinCode(),
	            order.getDeliveryAddress().getState()
	        );
	    }

	    // Map items
	    List<OrderItemResponse> itemResponses = order.getOrderItems().stream()
	        .map(item -> new OrderItemResponse(
	            item.getId(),
	            item.getFood().getName(),
	            item.getQuantity(),
	            item.getTotalPrice(),
	            item.getIngredients(),
	            item.getImage()
	        ))
	        .toList();

	    // Map customer (only required fields)
	    CustomerResponse customerDto = null;
	    if (order.getCustomer() != null) {
	        customerDto = new CustomerResponse(
	            order.getCustomer().getId(),
	            order.getCustomer().getFullName(),
	            order.getCustomer().getEmail()
	        );
	    }

	    return new OrderResponse(
	        order.getId(),
	        order.getOrderStatus(),
	        order.getCreateAt(),
	        order.getTotalPrice(),
	        order.getTotalItem(),
	        addr,
	        itemResponses,
	        customerDto
	    );
	}

//	@Override
//	public List<OrderResponse> getResturantOrder(Long resturantId, String orderStatus) throws Exception {
//		List<Order> orders = orderRepository.findByResturantId(resturantId);
//		
//		if (orderStatus != null) {
//			orders = orders.stream().filter(
//					order -> order.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
//		}
//		return orders.stream()
//                .map(this::convertToResponse)  // convert to DTO
//                .collect(Collectors.toList());
//	}

	@Override
	public List<OrderResponse> getResturantOrder(Long resturantId, String orderStatus) throws Exception {
		List<Order> orders = orderRepository.findByResturantIdWithItems(resturantId);

		if (orderStatus != null) {
			orders = orders.stream().filter(order -> order.getOrderStatus().equals(orderStatus)).toList();
		}

		return orders.stream().map(this::convertToResponse).toList();
	}

	@Override
	public Order findOrderById(Long orderId) {
		return orderRepository.findById(orderId).orElseThrow(() -> new UsernameNotFoundException("order not found"));
	}

}
