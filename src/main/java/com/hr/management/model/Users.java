package com.hr.management.model;

public class Users {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.USER_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    private Long userId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.USER_NAME
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    private String userName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.PASSWORD
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column USERS.ROLE_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    private Long roleId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.USER_ID
     *
     * @return the value of USERS.USER_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.USER_ID
     *
     * @param userId the value for USERS.USER_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.USER_NAME
     *
     * @return the value of USERS.USER_NAME
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public String getUserName() {
        return userName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.USER_NAME
     *
     * @param userName the value for USERS.USER_NAME
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.PASSWORD
     *
     * @return the value of USERS.PASSWORD
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.PASSWORD
     *
     * @param password the value for USERS.PASSWORD
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column USERS.ROLE_ID
     *
     * @return the value of USERS.ROLE_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column USERS.ROLE_ID
     *
     * @param roleId the value for USERS.ROLE_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}