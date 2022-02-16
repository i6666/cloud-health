package cc.mmail.interventionservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhuang.ma
 * @date 2022/2/15
 */
public class UserMapper {

    @Autowired
    private UserServiceClient userClient;

    private long id;
    private String userCode;
    private String userName;

    public UserMapper(long id, String userCode, String userName) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
    }



    @HystrixCommand(
            threadPoolKey = "springHealthGroup",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize",value = "2"),
                    @HystrixProperty(name = "maxQueueSize",value = "10")
            }
    )
    private UserMapper getUser(String userName){
        return userClient.getUserByUserName(userName);
    }


    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
