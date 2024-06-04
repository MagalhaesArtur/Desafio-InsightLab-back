package com.desafioInsightLab.repositories;

import com.desafioInsightLab.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository  extends JpaRepository<User, UUID> {
    User findUserByUsername(String username);
    UserDetails findByEmail(String email);
    User findUserByEmail(String email);

}
