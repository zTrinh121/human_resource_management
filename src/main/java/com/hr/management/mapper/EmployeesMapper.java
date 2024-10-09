package com.hr.management.mapper;

import com.hr.management.model.EmployeeFull;
import com.hr.management.model.Employees;
import com.hr.management.model.EmployeesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmployeesMapper {
    long countByExample(EmployeesExample example);

    int deleteByExample(EmployeesExample example);

    int deleteByPrimaryKey(Long employeeId);

    int insert(Employees row);

    int jobHi(Employees row);

    List<Employees> selectByExample(EmployeesExample example);

    EmployeeFull selectByPrimaryKey(Long employeeId);

    int updateByExampleSelective(@Param("row") Employees row, @Param("example") EmployeesExample example);

    int updateByExample(@Param("row") Employees row, @Param("example") EmployeesExample example);

    int updateByPrimaryKeySelective(Employees row);
    int updateByPrimaryKey(Employees row);

    List<EmployeeFull> selectEmployeesWithDetails();

    List<EmployeeFull> selectEmployeesWithDetailsActive();

    EmployeeFull selectEmployeesWithDetailsById(Long id);

    EmployeeFull selectByUserId(Long userId);

    EmployeeFull selectByEmail(String email);

    Employees selectByEmployeeId(Long id);

    int insertSelective(Employees row);

    EmployeeFull selectByDepartmentId(Long departmentId);

    void removingUserIdForEmployee(Long userId);

    Long isJobExisting(Long jobId);
    Long isDepartmentExisting(Long departmentId);
    Long setUserIdForEmployee(@Param("employeeId") Long employeeId, @Param("userId") Long userId);
    List<EmployeeFull> selectByUserIdNull();
    Long deleteSoftEmployee(Long employeeId);
    List<EmployeeFull> searchByKeyWord(String keyword);
    List<EmployeeFull> searchByKeyWordAndDepartment(@Param("keyword") String keyword, 
    @Param("departmentName") String departmentName);
    List<EmployeeFull> searchByDepartment(String departmentName);
    int updateByEmployeeId(Employees employees);


}