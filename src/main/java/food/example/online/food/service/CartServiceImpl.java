package food.example.online.food.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import food.example.online.food.dto.AddCartRequest;
import food.example.online.food.dto.UserDTO;
import food.example.online.food.entity.Cart;
import food.example.online.food.entity.CartItems;
import food.example.online.food.entity.Food;
import food.example.online.food.entity.Resturant;
import food.example.online.food.entity.User;
import food.example.online.food.repository.CartItemRepository;
import food.example.online.food.repository.CartRepository;
import food.example.online.food.repository.FoodRepository;
import food.example.online.food.repository.ResturantRepository;
import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService{
	
	private CartRepository cartRepository;
	private CartItemRepository cartItemRepository;
	private UserService userService;
	private FoodService foodService;
	private ResturantRepository resturantRepository;

	@Autowired
	public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository,
			UserService userService,FoodService foodService,
			ResturantRepository resturantRepository) {
		this.cartRepository = cartRepository;
		this.cartItemRepository = cartItemRepository;
		this.userService = userService;
		this.foodService = foodService;
		this.resturantRepository = resturantRepository;
	}

//	@Override
//	public CartItems addItemToCart(AddCartRequest req, String jwt) throws Exception {
//	     User user = userService.findUserByJwt(jwt);
//	     Food food = foodService.findFoodById(req.getFoodId());
//	     Cart cart = cartRepository.findByCustomerId(user.getId());
//	     
//	     for (CartItems items : cart.getListItems()) {
//	    	 if (items.getFood().equals(food)) {
//	    		 int newQuantity = items.getQuantity() + req.getQuantity();
//	    		 return updateCartItemQuantity(items.getId(), newQuantity);
//	    	 }
//	     }
//	     
//	    CartItems newCartItems = new CartItems();
//	     
//	     newCartItems.setFood(food);
//	     newCartItems.setCart(cart);
//	     newCartItems.setIngredients(req.getIngredients());
//	     newCartItems.setQuantity(req.getQuantity());
//	     newCartItems.setTotalPrice(req.getQuantity() * food.getPrice());
//	     
//	  // ✅ set restaurant
//	     if (req.getResturantId() != null) {
//	         Resturant resturant = resturantRepository.findById(req.getResturantId())
//	                 .orElseThrow(() -> new RuntimeException("Resturant not found"));
//	         newCartItems.setResturant(resturant);
//	     }
//	     
//	     CartItems savedItems = cartItemRepository.save(newCartItems);
//		
//	     cart.getListItems().add(savedItems);
//	     
//	     return savedItems;
//	}
	
	@Override
	public CartItems addItemToCart(AddCartRequest req, String jwt) throws Exception {
	    // 1️⃣ Find the user
	    User user = userService.findUserByJwt(jwt);

	    // 2️⃣ Get or create the user's cart
	    Cart cart = cartRepository.findByCustomerId(user.getId());
	    if (cart == null) {
	        cart = new Cart();
	        cart.setCustomer(user);
	        cart.setListItems(new ArrayList<>());
	        cart = cartRepository.save(cart); // save new cart
	    }

	    // 3️⃣ Get the food item
	    Food food = foodService.findFoodById(req.getFoodId());

	    // 4️⃣ Check if the food already exists in the cart
	    for (CartItems item : cart.getListItems()) {
	        if (item.getFood().getId().equals(food.getId())) { // safer equality check
	            int newQuantity = item.getQuantity() + req.getQuantity();
	            item.setQuantity(newQuantity);
	            item.setTotalPrice(newQuantity * food.getPrice());
	            return cartItemRepository.save(item); // update existing item
	        }
	    }

	    // 5️⃣ Create new cart item
	    CartItems newCartItem = new CartItems();
	    newCartItem.setFood(food);
	    newCartItem.setCart(cart);
	    newCartItem.setIngredients(req.getIngredients() != null ? req.getIngredients() : new ArrayList<>());
	    newCartItem.setQuantity(req.getQuantity());
	    newCartItem.setTotalPrice(req.getQuantity() * food.getPrice());

	    // 6️⃣ Set restaurant if provided
	    if (req.getResturantId() != null) {
	        Resturant resturant = resturantRepository.findById(req.getResturantId())
	                .orElseThrow(() -> new RuntimeException("Resturant not found"));
	        newCartItem.setResturant(resturant);
	    }

	    // 7️⃣ Save the new cart item
	    CartItems savedItem = cartItemRepository.save(newCartItem);

	    // 8️⃣ Add item to cart and save cart
	    cart.getListItems().add(savedItem);
	    cartRepository.save(cart);

	    return savedItem;
	}


	// CartServiceImpl.java
	@Transactional
	@Override
	public CartItems updateCartItemQuantity(Long cartItemId, int quantity, String jwt) throws Exception {
	    // 1. find user from jwt (same method you use elsewhere)
	    User user = userService.findUserByJwt(jwt);

	    // 2. find user's cart
	    Cart cart = cartRepository.findByCustomerId(user.getId());
	    if (cart == null) throw new Exception("Cart not found for user");

	    // 3. fetch item and validate it belongs to this cart
	    CartItems item = cartItemRepository.findById(cartItemId)
	        .orElseThrow(() -> new Exception("cart item not found"));

	    if (item.getCart() == null || !item.getCart().getId().equals(cart.getId())) {
	        throw new Exception("Item does not belong to this user's cart");
	    }

	    // 4. update and save
	    item.setQuantity(quantity);
	    item.setTotalPrice(item.getFood().getPrice() * quantity);

	    return cartItemRepository.save(item);
	}
	
	
	@Transactional
	@Override
	public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
	    User user = userService.findUserByJwt(jwt);

	    Cart cart = cartRepository.findByCustomerId(user.getId());
	    CartItems item = cartItemRepository.findById(cartItemId)
	        .orElseThrow(() -> new Exception("cart item not found"));

	    if (!item.getCart().getId().equals(cart.getId())) {
	        throw new Exception("Item does not belong to this user's cart");
	    }

	    cart.getListItems().remove(item); // orphanRemoval takes care of DB delete
	    return cartRepository.save(cart);
	}

	


	@Override
	public double calculateCartTotal(Cart cart) throws Exception {
		double total = 0.0;
		
		for (CartItems items : cart.getListItems()) {
			total += items.getFood().getPrice() * items.getQuantity();
		}
		return total;
	}

	@Override
	public Cart findCartById(Long id) throws Exception {
	    Optional<Cart> cart = cartRepository.findById(id);
	    if (cart.isEmpty()) {
	    	throw new Exception("cart not found");
	    }
		return cart.get();
	}

	
	@Override
	public Cart findCartByUserId(Long userId) throws Exception {
//		User user = userService.findUserByJwt(jwt);
		Cart cart = cartRepository.findByCustomerId(userId);
	     cart.setTotal(calculateCartTotal(cart));
	     return cart;
	}

	@Override
	public Cart clearCart(Long userId) throws Exception {
//		User user = userService.findUserByJwt(jwt);
	    Cart cart = findCartByUserId(userId);
	    
	    cart.getListItems().clear();
	   return cartRepository.save(cart);
	}

}
