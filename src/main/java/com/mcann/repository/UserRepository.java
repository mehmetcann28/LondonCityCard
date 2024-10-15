package com.mcann.repository;

import com.mcann.entity.User;
import com.mcann.views.VwUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select new com.mcann.views.VwUser(u.firstName,u.lastName,u.email,u.address,u.phone,u.username,u.birthday,u.cardType) from User u")
	List<VwUser> getAllVwUsers();
}