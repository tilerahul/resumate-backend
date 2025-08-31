package com.resumate.resumate_backend.Repo;

import com.resumate.resumate_backend.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
}
