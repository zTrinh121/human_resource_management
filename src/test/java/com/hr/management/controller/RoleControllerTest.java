package com.hr.management.controller;

import com.hr.management.component.JwtTokenUtil;
import com.hr.management.response.RolesResponse;
import com.hr.management.service.RoleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(RoleController.class)
@AutoConfigureMockMvc(addFilters = false)  // Disable security filters in tests
@AutoConfigureMybatis
@ActiveProfiles("test")
public class RoleControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private RoleService roleService;

    RolesResponse rolesResponse1;
    RolesResponse rolesResponse2;
    List<RolesResponse> rolesResponseList = new ArrayList<>();

    @BeforeEach
    void setUp(){
        rolesResponse1 = new RolesResponse(1L, "Admin");
        rolesResponse2 = new RolesResponse(2L, "User");
        rolesResponseList.add(rolesResponse1);
        rolesResponseList.add(rolesResponse2);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRoleById() throws Exception {
        doReturn(rolesResponse1).when(roleService).getRoleById(1L);

        this.mockMvc.perform(get("/api/v1/roles/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
