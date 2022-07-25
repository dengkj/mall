package com.dengkj.mall.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author dengkj
 * @time 2022-07-15 16:14:52
 * @description
 */
public interface UserRepository extends CrudRepository<User,Integer> {

    @Query(
            value = "SELECT * FROM user WHERE user_name = ?1",
            nativeQuery = true)
    User getByUserName(@RequestParam String userName);

}
