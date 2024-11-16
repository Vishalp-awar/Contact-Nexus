package com.ContactNexus.Repositories;

import com.ContactNexus.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositories extends JpaRepository<User,String> {

}
