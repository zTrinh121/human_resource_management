package com.hr.management.mapper;

import com.hr.management.model.EmployeeFull;
import com.hr.management.model.Employees;
import com.hr.management.model.EmployeesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeesMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EMPLOYEES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    long countByExample(EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EMPLOYEES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int deleteByExample(EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EMPLOYEES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int deleteByPrimaryKey(Long employeeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EMPLOYEES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int insert(Employees row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EMPLOYEES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int insertSelective(Employees row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EMPLOYEES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    List<Employees> selectByExample(EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EMPLOYEES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    Employees selectByPrimaryKey(Long employeeId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EMPLOYEES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByExampleSelective(@Param("row") Employees row, @Param("example") EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EMPLOYEES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByExample(@Param("row") Employees row, @Param("example") EmployeesExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EMPLOYEES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByPrimaryKeySelective(Employees row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table EMPLOYEES
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByPrimaryKey(Employees row);

    List<EmployeeFull> selectEmployeesWithDetails();

    EmployeeFull selectEmployeesWithDetailsById(Long id);

}