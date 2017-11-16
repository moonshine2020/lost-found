package com.nineteen.lostfound.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
@Entity
@Table(name="user")
public class User implements Serializable {
    /**
     * 登录名
     */
    @Id
    @Column(name="userid")
    private String userid;

    /**
     * 登录密码
     */
    @Column(name="password")
    private String password;

    /**
     * 是否被冻结
     */
    @Column(name="status")
    private Integer status;

    /**
     * 登录权限
     */
    @Column(name="privelege")
    private Integer privelege;

    /**
     * 真实姓名
     */
    @Column(name="truename")
    private String truename;

    /**
     * 上次登录时间
     */
    @Column(name="loaddate")
    private Date loaddate;

    private static final long serialVersionUID = 1L;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getPrivelege() {
        return privelege;
    }

    public void setPrivelege(Integer privelege) {
        this.privelege = privelege;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public Date getLoaddate() {
        return loaddate;
    }

    public void setLoaddate(Date loaddate) {
        this.loaddate = loaddate;
    }
}