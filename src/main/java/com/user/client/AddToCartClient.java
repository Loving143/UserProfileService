package com.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.request.AddToCartRequest;

@FeignClient(name="AddToCart",url="http://localhost:8044/cart")
public interface AddToCartClient {
	
	@PostMapping("/addToCart")
	public void AddToCart(@RequestBody AddToCartRequest req);
}
