package food.example.online.food.service;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//
//import com.stripe.Stripe;
//import com.stripe.exception.StripeException;
//import com.stripe.model.checkout.Session;
//import com.stripe.param.checkout.SessionCreateParams;
//
//import food.example.online.food.dto.OrderResponse;
//import food.example.online.food.dto.PaymentRespopnse;
//import food.example.online.food.entity.Order;
//
//@Service
//public class PaymentServiceImpl implements PaymentService{
//    
//	// Stripe api key
//	@Value("${stripe.api.key}")
//	private String stripeSecretKey;
//	
//	@Override
//	public PaymentRespopnse createPaymentLink(OrderResponse order) throws StripeException {
//		Stripe.apiKey = stripeSecretKey;
//		
//		SessionCreateParams params = SessionCreateParams.builder().
//				addPaymentMethodType
//				(SessionCreateParams.PaymentMethodType.CARD)
//				.setMode(SessionCreateParams.Mode.PAYMENT)
//				.setSuccessUrl("http://localhost:5173/payment/success/"+order.getId())
//				.setCancelUrl("http://localhost:5173/payment/fail")
//				
//				
//
//				
//				.addLineItem(SessionCreateParams.LineItem.builder()
//						.setQuantity(1l).setPriceData(SessionCreateParams.LineItem.PriceData.builder()
//								.setCurrency("usd")
//								.setUnitAmount((long)order.getTotalPrice()*100)
//								.setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
//										.setName("Nasir food")
//										.build())
//								.build()
//								)
//						.build()
//						)
//				.build();
//		
//		Session session = Session.create(params);
//		
//		PaymentRespopnse res = new PaymentRespopnse();
//		res.setPayment_url(session.getUrl());
//
//		
//		return res;
//	}
//	
//}





import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import food.example.online.food.dto.OrderResponse;
import food.example.online.food.dto.PaymentRespopnse;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class PaymentServiceImpl implements PaymentService {

    @Value("${stripe.api.key}")
    private String stripeSecretKey;

    @Value("${stripe.success.url}")
    private String successUrl;

    @Value("${stripe.cancel.url}")
    private String cancelUrl;

    @PostConstruct
    public void init() {
        System.out.println("Stripe Secret Key Loaded: " + (stripeSecretKey != null && !stripeSecretKey.isEmpty() ? "✅" : "❌"));
        System.out.println("Stripe Success URL: " + successUrl);
        System.out.println("Stripe Cancel URL: " + cancelUrl);
    }

    @Override
    public PaymentRespopnse createPaymentLink(OrderResponse order) throws StripeException {
        if (stripeSecretKey == null || stripeSecretKey.isEmpty()) {
            throw new IllegalStateException("Stripe API key not configured!");
        }

        Stripe.apiKey = stripeSecretKey;

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successUrl + "/payment/success/" + order.getId())
                .setCancelUrl(cancelUrl)
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("usd")
                                .setUnitAmount((long) order.getTotalPrice() * 100)
                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Nasir Food")
                                        .build())
                                .build())
                        .build())
                .build();

        Session session = Session.create(params);

        PaymentRespopnse res = new PaymentRespopnse();
        res.setPayment_url(session.getUrl());

        return res;
    }
}