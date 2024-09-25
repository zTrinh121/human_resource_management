package com.hr.management.service.impl;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.mapper.RolesMapper;
import com.hr.management.model.Roles;
import com.hr.management.model.RolesExample;
import com.hr.management.request.RolesRequest;
import com.hr.management.response.RolesResponse;
import com.hr.management.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    @Autowired
    RolesMapper rolesMapper;
    RolesExample rolesExample;
    @Override
    public RolesResponse getRoleById(Long roleId) {
        Roles role = rolesMapper.selectByPrimaryKey(roleId);
        if(role == null){
            throw new DataNotFoundException(String.format(
                    "Role not found with ID = %d", roleId));
        }
        return RolesResponse.fromRoles(role);
    }

    @Override
    public List<RolesResponse> getAllRoles() {
        List<Roles> rolesList = rolesMapper.selectByExample();
        return rolesList.stream()
                .map(RolesResponse::fromRoles).toList();
    }

    @Override
    public RolesResponse createRole(RolesRequest rolesRequest) {
        Roles roles = Roles.fromRoleRequest(rolesRequest);
        rolesMapper.insertRoleName(roles);
        return RolesResponse.fromRoles(roles);
    }

    @Override
    public RolesResponse updateRole(Long id, RolesRequest rolesRequest)  {
        Roles existingRole = rolesMapper.selectByPrimaryKey(id);
        if(existingRole == null){
            throw new DataNotFoundException("Not found role with id " + id);
        }
        existingRole.setRoleName(rolesRequest.getRoleName());

        rolesMapper.updateByPrimaryKeySelective(existingRole);

        return RolesResponse.fromRoles(existingRole);
    }

    @Override
    public void deleteRole(Long id) throws DataNotFoundException {
        Roles existingRole = rolesMapper.selectByPrimaryKey(id);
        if(existingRole == null){
            throw new DataNotFoundException("Not found role with id " + id);
        }
        rolesMapper.deleteByPrimaryKey(id);
    }


}
