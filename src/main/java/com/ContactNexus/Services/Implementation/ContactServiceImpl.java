package com.ContactNexus.Services.Implementation;

import com.ContactNexus.Entities.Contact;
import com.ContactNexus.Entities.User;
import com.ContactNexus.Helper.ResourceNotFoundException;
import com.ContactNexus.Repositories.ContactRepositories;
import com.ContactNexus.Services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepositories contactRepositories;

    @Override
    public Contact save(Contact contact) {

        String contactid = UUID.randomUUID().toString();
        contact.setId(contactid);

        return contactRepositories.save(contact);
    }

    @Override
    public Contact update(Contact contact) {

       var oldContact= contactRepositories.findById(contact.getId()).orElseThrow( () -> new ResourceNotFoundException("Contact Not Found byId"));

       oldContact.setName(contact.getName());
       oldContact.setEmail(contact.getEmail());
       oldContact.setAddress(contact.getAddress());
       oldContact.setPhoneNumber(contact.getPhoneNumber());
       oldContact.setDescription(contact.getDescription());
       oldContact.setFavorite(contact.isFavorite());
       oldContact.setWebsiteLink(contact.getWebsiteLink());
       oldContact.setLinkedInLink(contact.getLinkedInLink());
       oldContact.setCloudinaryImagePublicId(contact.getCloudinaryImagePublicId());
       oldContact.setPicture(contact.getPicture());
        return contactRepositories.save(oldContact);
    }

    @Override
    public List<Contact> getALl() {
        return contactRepositories.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepositories.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact Not found by id"));

    }

    @Override
    public void delete(String id) {
    var contact = contactRepositories.findById(id).orElseThrow(()-> new ResourceNotFoundException("Contact Not found by id"));
    contactRepositories.delete(contact);
    }

    @Override
    public List<Contact> search(String name, String email, String phoneNumber) {
        return List.of();
    }

    @Override
    public List<Contact> getByUserId(String userid) {

    return contactRepositories.findByUserId(userid);
    }

    @Override
    public Page<Contact> getByUser(User user,int page,int size,String sortBy, String direction) {

        Sort sort= direction.equals("desc")? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page,size,sort);
        return contactRepositories.findByUser(user,pageable);
    }


    @Override
    public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortBy, String order, User user) {

        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepositories.findByUserAndNameContaining(user, nameKeyword, pageable);
    }

    @Override
    public Page<Contact> searchByEmail(String emailKeyword, int size, int page, String sortBy, String order,
                                       User user) {
        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepositories.findByUserAndEmailContaining(user, emailKeyword, pageable);
    }

    @Override
    public Page<Contact> searchByPhoneNumber(String phoneNumberKeyword, int size, int page, String sortBy,
                                             String order, User user) {

        Sort sort = order.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);
        return contactRepositories.findByUserAndPhoneNumberContaining(user, phoneNumberKeyword, pageable);
    }
}
