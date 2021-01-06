package es.sb.utest.example.controller;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.sb.utest.example.domain.EsUserEntity;
import es.sb.utest.example.service.EsUserService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EsUserControllerTest {

    @Mock
    private EsUserService userService;

    @InjectMocks
    private EsUserController userController = new EsUserController();

    
	static EsUserEntity user1;
	static List<EsUserEntity> userList2 = new ArrayList<>();
	@BeforeClass
	public static void setUp() {
		user1 = new EsUserEntity(1, "password");
		userList2.add(new EsUserEntity(11, "password"));
		userList2.add(new EsUserEntity(12, "password2"));
	}
	
	
    @Test
    public void shouldCreateUser() throws Exception {
        EsUserEntity savedUser = stubServiceToReturnStoredUser();
        EsUserEntity returnedUser = userController.createUser(user1);
        // verify user was passed to UserService
        verify(userService, times(1)).saveUser(user1);
        assertEquals("Returned user should come from the service", savedUser, returnedUser);
    }

    private EsUserEntity stubServiceToReturnStoredUser() {
        when(userService.saveUser(any(EsUserEntity.class))).thenReturn(user1);
        return user1;
    }


    @Test
    public void shouldListAllUsers() throws Exception {
        stubServiceToReturnExistingUsers(10);
        Collection<EsUserEntity> users = userController.listUsers();
        assertNotNull(users);
        assertEquals(2, users.size());
        // verify user was passed to UserService
        verify(userService, times(1)).getAllUsers();
    }

    private void stubServiceToReturnExistingUsers(int howMany) {
        when(userService.getAllUsers()).thenReturn(userList2);
    }

}
