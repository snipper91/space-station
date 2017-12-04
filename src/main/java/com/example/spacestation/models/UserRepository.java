package com.example.spacestation.models;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    public User findByUsername(String username);
}
