package com.hr.management.mapper;

import com.hr.management.model.Roles;
import com.hr.management.model.RolesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    long countByExample(RolesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int deleteByExample(RolesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int deleteByPrimaryKey(Long roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int insert(Roles row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int insertSelective(Roles row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    List<Roles> selectByExample(RolesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    Roles selectByPrimaryKey(Long roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByExampleSelective(@Param("row") Roles row, @Param("example") RolesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByExample(@Param("row") Roles row, @Param("example") RolesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByPrimaryKeySelective(Roles row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByPrimaryKey(Roles row);

    int insertRoleName(Roles roles);
}