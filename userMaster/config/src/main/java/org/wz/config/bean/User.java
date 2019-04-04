package org.wz.config.bean;


import java.sql.Date;

/**
 * @project: userManager
 * @package: com.wonders.bean
 * @author: Administrator
 * @crDate: 2019/3/19 11:08
 * @editor: IntelliJ IDEA
 * @role:
 **/
public class User {
    private String id;
    private String userName;
    private String password;
    private Date createTime;
    private Date updateTime;
    private String isDelete;
    private String phone;
    private String sex;

    public User() {

    }

    public User(String id, String userName, String password, Date createTime, Date updateTime, String isDelete, String phone, String sex) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.isDelete = isDelete;
        this.phone = phone;
        this.sex = sex;
    }

    public User(String userName, Date createTime, String phone, String sex) {
        this.userName = userName;
        this.createTime = createTime;
        this.phone = phone;
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }
}
