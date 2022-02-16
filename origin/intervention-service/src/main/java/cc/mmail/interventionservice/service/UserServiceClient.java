package cc.mmail.interventionservice.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


/**
 * @author zhuang.ma
 * @date 2022/2/15
 */
@Component
@Slf4j
public class UserServiceClient {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    public UserMapper getUserByUserName(String userName) {

        ResponseEntity<UserMapper> response = restTemplate.exchange("http://userservice/users/{userName}",
                HttpMethod.GET,
                null, UserMapper.class, userName);

        return response.getBody();
    }


}
