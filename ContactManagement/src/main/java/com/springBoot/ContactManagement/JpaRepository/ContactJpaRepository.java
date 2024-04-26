package com.springBoot.ContactManagement.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springBoot.ContactManagement.Entites.ContactDeatil;

@Repository
public interface ContactJpaRepository extends JpaRepository<ContactDeatil, Long> {

	@Query("from ContactDeatil c where c.userObj.userId=:uId")
	public List<ContactDeatil> findContactByUserId(@Param("uId")int userId);
}
