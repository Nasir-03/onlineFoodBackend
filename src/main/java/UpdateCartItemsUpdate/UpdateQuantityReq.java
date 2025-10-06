package UpdateCartItemsUpdate;

public class UpdateQuantityReq {

	 private Long cartItemId;
	    private int quantity;
	    
		public Long getCartItemId() {
			return cartItemId;
		}
		public void setCartItemId(Long cartItemId) {
			this.cartItemId = cartItemId;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public UpdateQuantityReq(Long cartItemId, int quantity) {
			super();
			this.cartItemId = cartItemId;
			this.quantity = quantity;
		}
	    
	    
}
