package com.hr.management.serviceImpl;

import com.hr.management.exception.DataNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hr.management.mapper.RolesMapper;
import com.hr.management.model.Roles;
import com.hr.management.request.RolesRequest;
import com.hr.management.service.impl.RoleServiceImpl;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    @Mock
    private RolesMapper rolesMapper;

    @InjectMocks
    private RoleServiceImpl roleServiceImpl;

    private Roles roles;
    private Roles roles2;
    private RolesRequest rolesRequest;


    @BeforeEach
    void setUp() {
        roles = new Roles(1L, "user");
        roles2 = new Roles(2L, "admin");
        rolesRequest = new RolesRequest("user");
    }

    @Test
    void testGetRoleById_returnRolesResponse() {
        // given
        Mockito.when(rolesMapper.selectByPrimaryKey(roles.getRoleId())).thenReturn(roles);

        Assertions.assertEquals(roleServiceImpl.getRoleById(roles.getRoleId()).getRoleName(),
         roles.getRoleName());
    }


    @Test
    void testGetAllRoles_returnListRoleResponse(){
        List<Roles> rolesList = new ArrayList<>();
        rolesList.add(roles);
        rolesList.add(roles2);

        Mockito.when(rolesMapper.selectByExample())
        .thenReturn(rolesList);

        Assertions.assertEquals(2, rolesList.size());
        Assertions.assertEquals(roleServiceImpl.getAllRoles().get(0).getRoleName(), roles.getRoleName());
        Assertions.assertEquals(roleServiceImpl.getAllRoles().get(1).getRoleName(), roles2.getRoleName());

    }

    @Test
    void testCreateRole_returnRoleResponse() {
        Mockito.when(rolesMapper.insertRoleName(any(Roles.class))).thenReturn(1);

        Assertions.assertEquals(roles.getRoleName(), roleServiceImpl.createRole(rolesRequest).getRoleName());
    }

    @Test
    void testUpdateRole_returnRoleResponse() throws Exception {
        Mockito.when(rolesMapper.selectByPrimaryKey(roles.getRoleId())).thenReturn(roles);
        Mockito.when(rolesMapper.updateByPrimaryKeySelective(any(Roles.class))).thenReturn(1);

        Assertions.assertEquals(roles.getRoleName(), roleServiceImpl.updateRole(roles.getRoleId(), rolesRequest).getRoleName());
    }

    @Test
    void testDeleteRole_returnSuccess() throws DataNotFoundException {
        Mockito.when(rolesMapper.selectByPrimaryKey(roles.getRoleId())).thenReturn(roles);
        Mockito.when(rolesMapper.deleteByPrimaryKey(roles.getRoleId())).thenReturn(1);

        roleServiceImpl.deleteRole(roles.getRoleId());
        verify(rolesMapper, times(1)).deleteByPrimaryKey(roles.getRoleId());
    }

}
