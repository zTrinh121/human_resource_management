package com.hr.management.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hr.management.controller.DepartmentController;
import com.hr.management.controller.RoleController;
import com.hr.management.mapper.DepartmentsMapper;
import com.hr.management.model.Roles;
import com.hr.management.response.RolesResponse;
import com.hr.management.service.DepartmentService;
import com.hr.management.service.RoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(RoleController.class)
@SpringBootTest(
        classes = {
                RoleController.class
//                DepartmentService.class,
//                DepartmentsMapper.class
        }
)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class RoleControllerTest {
    @MockBean
    RoleService roleService;
    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testGetAllRole() throws Exception {
        Roles roles = new Roles(1L, "user");
        Roles roles1 = new Roles(2L, "admin");
        List<RolesResponse> rolesResponseList = new ArrayList<>();
        rolesResponseList.add(RolesResponse.fromRoles(roles1));
        rolesResponseList.add(RolesResponse.fromRoles(roles));

//        when(roleService.getAllRoles()).thenReturn(rolesResponseList);
        doReturn(rolesResponseList).when(roleService).getAllRoles();

        MvcResult result = mockMvc.perform(get("/api/v1/roles/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            String json = objectMapper.writeValueAsString(roles);
            System.out.println("Serialized JSON: " + json);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Response Status: " + result.getResponse().getStatus());
        System.out.println("Response Body: " + result.getResponse().getContentAsString());

        // Verify that the mock was called
        verify(roleService).getAllRoles();

        // Now perform your assertions
        mockMvc.perform(get("/api/v1/roles/all")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
