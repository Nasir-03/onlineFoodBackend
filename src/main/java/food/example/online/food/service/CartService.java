package food.example.online.food.service;

import food.example.online.food.dto.AddCartRequest;
import food.example.online.food.entity.Cart;
import food.example.online.food.entity.CartItems;

public interface CartService {

	public CartItems addItemToCart(AddCartRequest req,String jwt)throws Exception;
	
	public CartItems updateCartItemQuantity(Long cartItemId,int quantity,String jwt)throws Exception;

    public Cart removeItemFromCart(Long cartItemId,String jwt)throws Exception;

     public double calculateCartTotal(Cart cart)throws Exception;
     
     public Cart findCartById(Long id)throws Exception;
     
     public Cart findCartByUserId(Long userId)throws Exception;
     
     public Cart clearCart(Long userId)throws Exception;
}
