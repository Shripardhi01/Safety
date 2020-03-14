package com.dynast.womensafety.helper;

public class User {
    private Integer id =0;
    private String name;
    private String mobile;
    private String address;
    private String em1;
    private String em2;
    private String em3;
    private String police;

    public User(Integer id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
    }

    public User(Integer id, String name, String mobile, String address, String em1, String em2, String em3) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.em1 = em1;
        this.em2 = em2;
        this.em3 = em3;
    }

    public User(Integer id, String name, String mobile, String address, String em1, String em2, String em3, String police) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.address = address;
        this.em1 = em1;
        this.em2 = em2;
        this.em3 = em3;
        this.police = police;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getAddress() {
        return address;
    }

    public String getEm1() {
        return em1;
    }

    public String getEm2() {
        return em2;
    }

    public String getEm3() {
        return em3;
    }

    public String getPolice() {
        return police;
    }
}
