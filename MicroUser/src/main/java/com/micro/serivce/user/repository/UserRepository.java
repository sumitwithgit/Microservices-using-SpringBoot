package com.micro.serivce.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.micro.serivce.user.entities.User;

public interface UserRepository extends JpaRepository<User, String>{

}
