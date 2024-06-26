package com.springBoot.ContactManagement.JpaRepository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springBoot.ContactManagement.Entites.ContactDeatil;
import com.springBoot.ContactManagement.Entites.User;

@Repository
public interface ContactJpaRepository extends JpaRepository<ContactDeatil, Long> {

	/**
	 * Pageable interface hold the information for current page and number of records we want to show 
	 * and it will give it to Page interface 
	 * Page interface is subList of List interface 
	 * @param userId
	 * @param pageable
	 * @return
	 */
	@Query("from ContactDeatil c where c.userObj.userId=:uId")
	public Page<ContactDeatil> findContactByUserId(@Param("uId")int userId,Pageable pageable);
	
	public ContactDeatil findAllByContactId(Long cid);
	
	@Query(value="select * from contact_deatil where contact_name like %:name% and user_Id=:uId",nativeQuery=true)
	public List<ContactDeatil> findByContactNameAndUser(@Param("name")String contactName,@Param("uId")int loginUserId );
}
