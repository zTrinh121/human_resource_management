package com.hr.management.mapper;

import com.hr.management.model.Users;
import com.hr.management.model.UsersExample;
import com.hr.management.model.UsersFull;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    long countByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int deleteByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int insert(Users row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int insertSelective(Users row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    List<Users> selectByExample(UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    Users selectByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByExampleSelective(@Param("row") Users row, @Param("example") UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByExample(@Param("row") Users row, @Param("example") UsersExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByPrimaryKeySelective(Users row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USERS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByPrimaryKey(Users row);

    List<UsersFull> getAllUserDetail();
    UsersFull getAllUserDetailByUserId(Long userId);
    int insertUserCustom(Users user);
    Users selectByUserName(String userName);
    Users selectByUserNameAndId(@Param("userName") String userName, @Param("userId") Long userId);
    UsersFull getAllUserDetailByUserName(String userName);

    int updateImage(@Param("imgUrl") String imgUrl, @Param("publicId") String publicId, @Param("userId") Long userId);
}