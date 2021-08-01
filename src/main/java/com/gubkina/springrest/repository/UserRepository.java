package com.gubkina.springrest.repository;

import com.gubkina.springrest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User user join fetch user.rolesOfUser where user.username = :username")
    User findByUsername(@Param("username") String username);

    @Override
    @Query("from User user join fetch user.rolesOfUser")
    List<User> findAll();

    @Override
    @Query("from User user join fetch user.rolesOfUser where user.id = :id")
    Optional<User> findById(@Param("id") Long id);
}
