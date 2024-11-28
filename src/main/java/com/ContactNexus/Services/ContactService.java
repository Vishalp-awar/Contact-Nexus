package com.ContactNexus.Services;

import com.ContactNexus.Entities.Contact;

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

}
