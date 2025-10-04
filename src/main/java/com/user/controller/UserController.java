package com.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.client.LoginClient;
import com.user.dto.ChangePasswordRequest;
import com.user.request.UserProfileRequest;
import com.user.response.UserProfileResponse;
import com.user.service.UserService;

@RestController
@RequestMapping("/api/user-profiles")
public class UserController {
	    
	    @Autowired
	    private UserService userProfileService;
	    private final LoginClient loginClient;
	    public UserController(LoginClient loginClient) {
	    	this.loginClient = loginClient;
	    }
	    @PostMapping("/create")	
	    @PreAuthorize("hasRole('USER')")
	    public ResponseEntity<?> createUserProfile(@RequestBody UserProfileRequest request) {
	        UserProfileResponse response = userProfileService.createUserProfile(request);
	        return ResponseEntity.ok(response);
	    }
	    
	    @PreAuthorize("hasRole('USER')")
	    @GetMapping("/profile/{userName}")
	    public ResponseEntity<UserProfileResponse> getMyProfile(@PathVariable String userName) {
	        UserProfileResponse response = userProfileService.getUserProfileByUserName(userName);
	        return ResponseEntity.ok(response);
	    }
	    
	    @PutMapping("/update/{userName}")
	    public ResponseEntity<UserProfileResponse> updateUserProfile(@PathVariable String userName, @RequestBody UserProfileRequest request) {
	        UserProfileResponse response = userProfileService.updateUserProfile(userName, request);
	        return ResponseEntity.ok(response);
	    }
	    
	    @DeleteMapping("/delete/{userName}")
	    public ResponseEntity<?> deleteUserProfile(@PathVariable String userName) {
	        userProfileService.deleteUserProfile(userName);
	        return ResponseEntity.ok("Profile deleted successfully!");
	    }
	    
	    @GetMapping("/blood-group/{bloodGroup}")
	    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
	    public ResponseEntity<List<UserProfileResponse>> getProfilesByBloodGroup(
	            @PathVariable String bloodGroup) {
	        List<UserProfileResponse> responses = userProfileService.getProfilesByBloodGroup(bloodGroup);
	        return ResponseEntity.ok(responses);
	    }
	    
	    @GetMapping("/completeness/{userName}")
	    public ResponseEntity<Map<String, Object>> getProfileCompleteness(@PathVariable String userName) {
	        UserProfileResponse profile = userProfileService.getUserProfileByUserName(userName);
	        Map<String, Object> completeness = new HashMap<>();
	        completeness.put("hasBloodGroup", profile.getBloodGroup() != null);
	        completeness.put("hasPhoneNumber", profile.getPhoneNumber() != null);
	        completeness.put("hasDateOfBirth", profile.getDateOfBirth() != null);
	        completeness.put("completenessScore", calculateCompletenessScore(profile));
	        return ResponseEntity.ok(completeness);
	    }
	    
	    private int calculateCompletenessScore(UserProfileResponse profile) {
	        int score = 0;
	        if (profile.getFirstName() != null) score += 20;
	        if (profile.getLastName() != null) score += 20;
	        if (profile.getPhoneNumber() != null) score += 15;
	        if (profile.getDateOfBirth() != null) score += 15;
	        if (profile.getBloodGroup() != null) score += 15;
//	        if (profile.getGender() != null) score += 15;
	        return score;
	    }
	    
	    @PostMapping("/changePassword")
	    public ResponseEntity<?>changePassWord(@RequestBody ChangePasswordRequest req){
	    	return loginClient.changePassword(req);
	    }
	}