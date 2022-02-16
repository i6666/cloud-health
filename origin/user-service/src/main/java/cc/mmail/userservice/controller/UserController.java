package cc.mmail.userservice.controller;

import cc.mmail.userservice.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhuang.ma
 * @date 2022/2/15
 */
@RestController
@RequestMapping(value = "users")
@Slf4j
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/{userName}", method = RequestMethod.GET)
    public User getUserByUserName(@PathVariable("userName") String userName) {

        log.info("Get user by userName from port : {} of userservice instance", request.getServerPort());

        User user = new User();
        user.setId(1L);
        user.setUserCode("mockUser");
        user.setUserName(userName);
        return user;
    }
}
