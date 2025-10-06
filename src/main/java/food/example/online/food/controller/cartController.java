package food.example.online.food.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import food.example.online.food.dto.AddCartRequest;
import food.example.online.food.dto.UpdateCartItemsUpdate;
import food.example.online.food.entity.Cart;
import food.example.online.food.entity.CartItems;
import food.example.online.food.entity.User;
import food.example.online.food.service.CartService;
import food.example.online.food.service.UserService;

@RestController
@RequestMapping("/api")
public class cartController {

	private CartService cartService;
	private UserService userService;

	@Autowired
	public cartController(CartService cartService,UserService userService) {
		this.cartService = cartService;
		this.userService = userService;
	}
	
	@PutMapping("/cart/add")
	public ResponseEntity<CartItems> addItemsTocart(
			@RequestBody AddCartRequest req, @RequestHeader("Authorization") String jwt) throws Exception{
		CartItems items = cartService.addItemToCart(req, jwt);
	  return new ResponseEntity<CartItems>(items,HttpStatus.OK);
	}
	
//	@PutMapping("/cart-item/update")
//	public ResponseEntity<CartItems> updateCartItemQuantity(
//			@RequestBody UpdateCartItemsUpdate req) throws Exception{
//		CartItems items = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity());
//	  return new ResponseEntity<CartItems>(items,HttpStatus.CREATED);
//	}
	
	@PutMapping("/cart-item/update")
	public ResponseEntity<CartItems> updateCartItemQuantity(
	        @RequestBody UpdateCartItemsUpdate req,
	        @RequestHeader("Authorization") String jwt) throws Exception {
	    CartItems items = cartService.updateCartItemQuantity(req.getCartItemId(), req.getQuantity(), jwt);
	    return new ResponseEntity<>(items, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cart-item/{id}/remove")
	public ResponseEntity<Cart> removeItemFromCart(
			@PathVariable Long id,@RequestHeader("Authorization") String jwt) throws Exception{
		Cart items = cartService.removeItemFromCart(id, jwt);
	  return new ResponseEntity<>(items,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cart/clear")
	public ResponseEntity<Cart> clearCart(
			@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwt(jwt);
		Cart items = cartService.clearCart(user.getId());
	  return new ResponseEntity<>(items,HttpStatus.CREATED);
	}
	
	@GetMapping("/cart")
	public ResponseEntity<Cart> findUserCart(
			@RequestHeader("Authorization") String jwt) throws Exception{
		User user = userService.findUserByJwt(jwt);
		Cart items = cartService.findCartByUserId(user.getId());
	  return new ResponseEntity<>(items,HttpStatus.OK);
	}
}
