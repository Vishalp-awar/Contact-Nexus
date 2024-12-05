package com.ContactNexus.Services;

import com.ContactNexus.Entities.Contact;
import com.ContactNexus.Entities.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContactService {

//    save contact
    Contact save(Contact contact);

//    update contact
    Contact update(Contact contact);

//    get contact
    List<Contact> getALl();

//    get contact by id
    Contact getById(String id);

//    delete contact
    void delete(String id);

//    search contact
    List<Contact> search(String name,String email,String phoneNumber);

//    get by userid
     List<Contact> getByUserId(String userId);

    // get contacts by user
    Page<Contact> getByUser(User user,int page,int size,String sortField,String sortDirection);


    // search contact
    Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order, User user);

    Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy, String order,
                                      User user);
}
