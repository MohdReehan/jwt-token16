package com.max.max.repsitories;



import com.max.max.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findById(Long aLong);

    Optional findByName(String name);
}
