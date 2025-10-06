package food.example.online.food.service;

import com.stripe.exception.StripeException;

import food.example.online.food.dto.OrderResponse;
import food.example.online.food.dto.PaymentRespopnse;

public interface PaymentService {

	public PaymentRespopnse createPaymentLink(OrderResponse order) throws StripeException;
}
