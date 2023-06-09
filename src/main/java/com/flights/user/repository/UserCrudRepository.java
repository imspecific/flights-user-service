package com.flights.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.flights.user.model.User;

@Repository
public interface UserCrudRepository extends CrudRepository<User, Integer> {

	Optional<User> findByUserNameAndPassword(String userName, String password);
	Optional<User> findByUserNameAndPasswordAndUserType(String userName, String password, String userType);
}
