package com.ntg.clothes_online_project.repository;


import com.ntg.clothes_online_project.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);

    User findByEmail(String email);
}
