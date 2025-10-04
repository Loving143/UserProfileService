package com.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.user.dto.ChangePasswordRequest;
import com.user.dto.PasswordChangeResult;

@FeignClient(name = "identity-service", url = "http://localhost:8040/auth")
public interface LoginClient {

	@PostMapping("/changePassword")
	public ResponseEntity<PasswordChangeResult> changePassword(@RequestBody ChangePasswordRequest req);
}
