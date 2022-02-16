package cc.mmail.userservice;

import cc.mmail.userservice.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@SpringBootTest
class UserServiceApplicationTests {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Test
    void contextLoads() {
        System.out.println("------------");
    }
    @Test
    void testGetUserByUserName(){
        List<ServiceInstance> instances = discoveryClient.getInstances("userservice");

        if (instances.size()==0)
            return ;

        String userserviceUri = String.format("%s/users/%s",instances.get(0).getUri()
                .toString(),"张三");

        ResponseEntity<User> user =
                restTemplate.exchange(userserviceUri, HttpMethod.GET, null, User.class, "张三");

    }




}
