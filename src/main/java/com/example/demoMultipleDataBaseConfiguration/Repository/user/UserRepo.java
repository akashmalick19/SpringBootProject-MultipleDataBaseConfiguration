package com.example.demoMultipleDataBaseConfiguration.Repository.user;

import com.example.demoMultipleDataBaseConfiguration.Model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    @Query(value = "select * from user u where u.userEmail= :email",
    nativeQuery = true
    )
    User findByEmail(@Param("email") String userEmail);

    @Query(value = "select * from user u where u.userName= ?1",
            nativeQuery = true
    )
    List<User> findByName(String userName);

}
