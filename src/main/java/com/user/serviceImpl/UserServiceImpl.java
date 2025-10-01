package com.user.serviceImpl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.entity.UserProfiles;
import com.user.repository.UserRepository;
import com.user.request.UserProfileRequest;
import com.user.response.UserProfileResponse;
import com.user.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService{

    
    @Autowired
    private UserRepository userRepository;
    
    // CREATE - Create new user profile
    public UserProfileResponse createUserProfile(String userName, UserProfileRequest request) throws BadRequestException {
        // Check if profile already exists
        if (userRepository.existsByUserName(userName)) {
            throw new BadRequestException("User profile already exists for user: " + userName);
        }
        
        UserProfiles profile = mapToEntity(userName, request);
        UserProfiles savedProfile = userRepository.save(profile);
        
        return mapToResponse(savedProfile);
    }
    
    // READ - Get user profile by user ID
    public UserProfileResponse getUserProfileByUserId(Long userId) throws BadRequestException {
        UserProfiles profile = userRepository.findByUserId(userId)
                .orElseThrow(() -> new BadRequestException("UserProfile"));
        
        return mapToResponse(profile);
    }
    
    // READ - Get user profile by profile ID
    public UserProfileResponse getUserProfileById(Long id) throws BadRequestException {
        UserProfiles profile = userRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("UserProfile"));
        
        return mapToResponse(profile);
    }
    
    // UPDATE - Update user profile
    public UserProfileResponse updateUserProfile(Long userId, UserProfileRequest request) throws BadRequestException {
        UserProfiles existingProfile = userRepository.findByUserId(userId)
                .orElseThrow(() -> new BadRequestException("UserProfile"));
        
        // Update fields
        updateEntityFromRequest(existingProfile, request);
        UserProfiles updatedProfile = userRepository.save(existingProfile);
        
        return mapToResponse(updatedProfile);
    }
    
    
    
    // DELETE - Delete user profile
    public void deleteUserProfile(Long userId) throws BadRequestException {
        UserProfiles profile = userRepository.findByUserId(userId)
                .orElseThrow(() -> new BadRequestException("UserProfile not exists!"));
        userRepository.delete(profile);
    }
    
    // Additional business methods
    public List<UserProfileResponse> getProfilesByBloodGroup(String bloodGroup) {
        List<UserProfiles> profiles = userRepository.findByBloodGroup(bloodGroup);
        return profiles.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }
    
    public UserProfileResponse updateBloodGroup(String userName, String bloodGroup) throws BadRequestException {
        UserProfiles profile = userRepository.findByUserName(userName)
                .orElseThrow(() -> new BadRequestException("UserProfile"));
        
        profile.setBloodGroup(bloodGroup);
        UserProfiles updatedProfile = userRepository.save(profile);
        
        return mapToResponse(updatedProfile);
    }
    
    // Helper methods
    private UserProfiles mapToEntity(String userName, UserProfileRequest request) {
    	UserProfiles profile = new UserProfiles(userName, request);
    	return profile;
    	
    }
    
    private void updateEntityFromRequest(UserProfiles profile, UserProfileRequest request) {
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setDateOfBirth(request.getDateOfBirth());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setBloodGroup(request.getBloodGroup());
        profile.setProfilePicture(request.getProfilePicture());
    }
    
    private UserProfileResponse mapToResponse(UserProfiles profile) {
        return null;
    }

	@Override
	public UserProfileResponse createUserProfile(UserProfileRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfileResponse getUserProfileByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfileResponse updateUserProfile(String userName, UserProfileRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserProfile(String userName) {
		// TODO Auto-generated method stub
		
	}
}