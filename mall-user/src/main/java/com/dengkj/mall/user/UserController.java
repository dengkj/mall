package com.dengkj.mall.user;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author dengkj
 * @time 2022-07-15 16:15:56
 * @description
 */
@RestController
@RequestMapping("/mall/user")
public class UserController {

    @Resource
    private UserRepository userRepository;

    @PostMapping("/add")
    public @ResponseBody
    String addNewUser(@RequestParam String userName, @RequestParam String password
            , @RequestParam String email, @RequestParam Byte auth) {
        User u = new User();
        u.setUserName(userName);
        u.setPassword(password);
        u.setEmail(email);
        u.setAuth(auth);
        userRepository.save(u);
        return "Saved";
    }

    @GetMapping("/all")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/getOne")
    public @ResponseBody
    User getByUserName(@RequestParam String userName) {
        return userRepository.getByUserName(userName);
    }

}
