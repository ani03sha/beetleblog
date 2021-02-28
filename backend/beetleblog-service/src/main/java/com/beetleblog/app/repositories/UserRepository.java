package com.beetleblog.app.repositories;

import com.beetleblog.app.domains.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
