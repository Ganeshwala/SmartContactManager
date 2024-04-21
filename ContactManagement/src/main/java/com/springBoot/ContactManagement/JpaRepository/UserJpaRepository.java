package com.springBoot.ContactManagement.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springBoot.ContactManagement.Entites.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.loginName=:loginName and u.password=:pass")
	public User getUserDetailsByUserNameAndPassword(@Param("loginName")String loginName,@Param("pass")String Pass);
	
	@Query("select u from User u where u.loginName=:loginName")
	public User getUserDetailsByUserName(@Param("loginName")String loginName);
}
