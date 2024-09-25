package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hr.management.controller.DepartmentController;
import com.hr.management.mapper.DepartmentsMapper;
import com.hr.management.model.DepartmentsFull;
import com.hr.management.response.DepartmentsResponse;
import com.hr.management.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@WebMvcTest(DepartmentController.class)
@SpringBootTest(
        classes = {
                DepartmentController.class,
                DepartmentService.class,
                DepartmentsMapper.class
        }
)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    private DepartmentController departmentController;
    @MockBean
    private  DepartmentService departmentService;
    DepartmentsFull departmentsFull1;
    DepartmentsFull departmentsFull2;
    List<DepartmentsFull> departmentsFullList = new ArrayList<>();
    List<DepartmentsResponse> departmentsResponseList = new ArrayList<>();
    private final String PATH_URL = "http://localhost:8080/api/v1/";

    @BeforeEach
    void setUp(){
        //mockMvc = MockMvcBuilders.standaloneSetup(departmentController).build();
        departmentsFull1 = new DepartmentsFull(1L, "IT", 1L, "John Doe");
        departmentsFull2 = new DepartmentsFull(1L, "Sale", 1L, "Jane Doe");
        departmentsFullList.add(departmentsFull1);
        departmentsFullList.add(departmentsFull2);
        departmentsResponseList.add(DepartmentsResponse.fromDepartmentFull(departmentsFull1));
        departmentsResponseList.add(DepartmentsResponse.fromDepartmentFull(departmentsFull2));
    }

    @Test
    void getDepartmentById() throws Exception {
        when(departmentService.getDepartmentById(departmentsFull1.getDepartmentId()))
                .thenReturn(DepartmentsResponse.fromDepartmentFull(departmentsFull1));

        System.out.println(departmentService.getDepartmentById(departmentsFull1.getDepartmentId()).toString());

        mockMvc.perform(get(PATH_URL + "departments/" + departmentsFull1.getDepartmentId())
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.department_name", is("IT")));
    }

    @Test
    void testGetAllDepartment() throws Exception {
        when(departmentService.getAllDepartments()).thenReturn(departmentsResponseList);


        this.mockMvc.perform(get(PATH_URL + "departments/all")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)));
    }

}
