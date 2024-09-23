package serviceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hr.management.mapper.JobsMapper;
import com.hr.management.mapper.RolesMapper;
import com.hr.management.mapper.UsersMapper;
import com.hr.management.model.Jobs;
import com.hr.management.model.Users;
import com.hr.management.model.UsersFull;
import com.hr.management.request.JobsRequest;
import com.hr.management.request.UsersRequest;
import com.hr.management.response.UsersResponse;
import com.hr.management.service.impl.JobServiceImpl;
import com.hr.management.service.impl.UserServiceImpl;

public class UserServiceImplTest {

    @Mock
    private UsersMapper usersMapper;
    private RolesMapper rolesMapper;
    private UserServiceImpl userService;;
    AutoCloseable autoCloseable;
    UsersFull users;
    UsersRequest usersRequest;

    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(usersMapper, rolesMapper);
        users = new UsersFull(1L, "John Doe", "johndoe123", 1L, "admin");
        usersRequest = new UsersRequest("John Doe", "password", 1L);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testGetUserById_returnUserResponse() throws Exception {
        mock(Users.class);
        mock(UsersMapper.class);

        when(usersMapper.getAllUserDetailByUserId(1L)).thenReturn(users);
        assertEquals(userService.getUserById(users.getUserId()).getUserName(), 
        users.getUserName());
    }

    @Test 
    void testCreateUser_returnUserResponse() throws Exception {
        mock(Users.class);
        mock(UsersMapper.class);



    }
}
