package com.shoppy.Shoppy.repository;

import com.shoppy.Shoppy.entity.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEmailRepository extends JpaRepository<UserEmail, Integer> {

    UserEmail findByEmail(String email);

    @Query(value="select u from UserEmail u where u.user.userId=?1 and u.isPrimary="+true)
    UserEmail findByUserIdAndIsPrimary(Integer userId);
}
