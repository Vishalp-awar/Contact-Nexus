package com.ContactNexus.Repositories;

import com.ContactNexus.Entities.Contact;
import com.ContactNexus.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    Page<Contact> findByUser(User user, Pageable pageable);

//      find by user id
//custom query method
@Query("SELECT c FROM Contact c WHERE c.user.id = :userid")
List<Contact> findByUserId(@Param("userid") String userid);
    
//    List<Contact> findByUserId(String userId);
Page<Contact> findByUserAndNameContaining(User user, String namekeyword, Pageable pageable);

    Page<Contact> findByUserAndEmailContaining(User user, String emailkeyword, Pageable pageable);

    Page<Contact> findByUserAndPhoneNumberContaining(User user, String phonekeyword, Pageable pageable);

}
