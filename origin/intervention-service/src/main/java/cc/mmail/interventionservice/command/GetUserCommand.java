package cc.mmail.interventionservice.command;

import cc.mmail.interventionservice.service.UserMapper;
import cc.mmail.interventionservice.service.UserServiceClient;
import com.netflix.hystrix.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhuang.ma
 * @date 2022/2/15
 */
public class GetUserCommand extends HystrixCommand<UserMapper> {

    @Autowired
    private UserServiceClient userServiceClient;

    protected GetUserCommand(String name) {
        super(Setter.withGroupKey( //设置命令组
                HystrixCommandGroupKey.Factory.asKey("springHealthGroup"))
                //设置线程键
                .andCommandKey(HystrixCommandKey.Factory.asKey("interventionKey"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey(name))

                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                        .withExecutionTimeoutInMilliseconds(5000)
                )
                .andThreadPoolPropertiesDefaults(
                        HystrixThreadPoolProperties.Setter()
                        .withMaxQueueSize(10)
                        .withCoreSize(2)
                )


        );
    }


    @Override
    protected UserMapper run() throws Exception {
        return userServiceClient.getUserByUserName("springhealth_user1");
    }

    @Override
    protected UserMapper getFallback() {
        return new UserMapper(1L,"user1","springhealth_user1");
    }
}
