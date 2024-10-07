package com.hr.management.mapper;

import com.hr.management.model.Roles;
import com.hr.management.model.RolesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolesMapper {
    long countByExample(RolesExample example);

    int deleteByExample(RolesExample example);

    int deleteByPrimaryKey(Long roleId);

    int insert(Roles row);

    int insertSelective(Roles row);

    List<Roles> selectByExample();

    Roles selectByPrimaryKey(Long roleId);

    int updateByExampleSelective(@Param("row") Roles row, @Param("example") RolesExample example);

    int updateByExample(@Param("row") Roles row, @Param("example") RolesExample example);

    int updateByPrimaryKeySelective(Roles row);

    int updateByPrimaryKey(Roles row);

    int insertRoleName(Roles roles);

    List<Roles> getRolebyName(String roleName);
}