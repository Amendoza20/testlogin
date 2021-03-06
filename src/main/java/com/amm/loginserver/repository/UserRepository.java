package com.amm.loginserver.repository;

import com.amm.loginserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
//    User findUserById(Long userId);
}
