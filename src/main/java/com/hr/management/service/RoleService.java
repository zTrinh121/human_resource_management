package com.hr.management.service;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.request.JobsRequest;
import com.hr.management.request.RolesRequest;
import com.hr.management.response.JobsResponse;
import com.hr.management.response.RolesResponse;

import java.util.List;

public interface RoleService {

    RolesResponse getRoleById(Long id);

    List<RolesResponse> getAllRoles();

    RolesResponse createRole(RolesRequest rolesRequest);

    RolesResponse updateRole(Long id, RolesRequest rolesRequest) ;

    void deleteRole(Long id) throws DataNotFoundException;

}
