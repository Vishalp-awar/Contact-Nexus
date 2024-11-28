package com.ContactNexus.Repositories;

import com.ContactNexus.Entities.Contact;
import com.ContactNexus.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepositories extends JpaRepository<Contact,String>
{
//    find contact by user
//custom finder method
    List<Contact> findByUser(User user);

//      find by user id
//custom query method
//    @Query("SELECT c FROM Contact WHERE c.user.id = :userid")
//    List<Contact> findByUserId(@Param("userid") String userid);
}
