package com.wine.model;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Judith on 21.05.2017.
 */

@Transactional
public interface UserDao extends JpaRepository<User, Long> {
    public User getUserByUsername(String username);
}
