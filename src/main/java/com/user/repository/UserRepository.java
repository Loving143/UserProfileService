package com.user.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.user.entity.UserProfiles;

@Repository
public interface UserRepository extends JpaRepository<UserProfiles, Long>{

	boolean existsByUserId(Long userId);

	List<UserProfiles> findByBloodGroup(String bloodGroup);

	Optional<UserProfiles> findByUserId(Long userId);

	boolean existsByUserName(String userName);

	Optional<UserProfiles> findByUserName(String userName);

}
