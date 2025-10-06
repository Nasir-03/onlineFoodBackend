package food.example.online.food.service;

import java.util.List;

import food.example.online.food.dto.OrderRequest;
import food.example.online.food.dto.OrderResponse;
import food.example.online.food.entity.Order;
import food.example.online.food.entity.User;

public interface OrderService {

	public OrderResponse createOrder(OrderRequest order,User user)throws Exception;
	
	public Order updateOrder(Long orderId,String orderStatus)throws Exception;

    public void cancelOrder(Long orderId)throws Exception;
    
    public List<OrderResponse> getUserOrder(Long userId)throws Exception;
    
    public List<OrderResponse> getResturantOrder(Long resturantId,String orderStatus)throws Exception;

   public Order findOrderById(Long orderId);
}
