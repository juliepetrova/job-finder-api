package com.webapp.ui.repository;

import com.webapp.ui.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findAll();

    User findById(int id);

    User findByEmail(String email);

    User findByUsername(String username);

    User save(User user);


}
