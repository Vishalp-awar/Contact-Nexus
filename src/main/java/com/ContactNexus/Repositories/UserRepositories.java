package com.ContactNexus.Repositories;

import com.ContactNexus.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositories extends JpaRepository<User,String> {

    Optional<User> findByEmail(String email);


}
