package cc.mmail.userservice.entity;

/**
 * @author zhuang.ma
 * @date 2022/2/15
 */
public class User {

    private long id;
    private String userCode;
    private String userName;

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
