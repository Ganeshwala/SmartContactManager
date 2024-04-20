package com.springBoot.ContactManagement.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springBoot.ContactManagement.Entites.User;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Integer> {

}
