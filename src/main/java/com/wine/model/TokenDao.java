package com.wine.model;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

/**
 * Created by Judith on 21.05.2017.
 */

@Transactional
public interface TokenDao extends JpaRepository<Token, Long> {
    public Token getTokenByValue(String value);
}
