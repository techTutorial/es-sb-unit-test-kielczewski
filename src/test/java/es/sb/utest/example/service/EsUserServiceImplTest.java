package es.sb.utest.example.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.sb.utest.example.domain.EsUserEntity;
import es.sb.utest.example.repository.EsUserRepository;
import es.sb.utest.example.service.EsUserService;
import es.sb.utest.example.service.exception.EsUserAlreadyExistsException;
import es.sb.utest.example.service.impl.EsUserServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EsUserServiceImplTest {

    @Mock
    private EsUserRepository userRepository;

    @InjectMocks
    private EsUserService userService = new EsUserServiceImpl();
    
    
	static EsUserEntity user1;
	static List<EsUserEntity> emptyUserList = new ArrayList<>();
	static List<EsUserEntity> userList2 = new ArrayList<>();
	
	@BeforeClass
	public static void setUp() {
		user1 = new EsUserEntity(99, "password99");
		userList2.add(new EsUserEntity(11, "password"));
		userList2.add(new EsUserEntity(12, "password2"));
	}

	
    @Test
    public void shouldSaveNewUser_GivenThereDoesNotExistOneWithTheSameId_ThenTheSavedUserShouldBeReturned() throws Exception {
        EsUserEntity savedUser = stubRepositoryToReturnUserOnSave();
        EsUserEntity returnedUser = userService.saveUser(user1);
        // verify repository was called with user
        verify(userRepository, times(1)).save(user1);
        assertEquals("Returned user should come from the repository", savedUser, returnedUser);
    }

    private EsUserEntity stubRepositoryToReturnUserOnSave() {
        when(userRepository.save(any(EsUserEntity.class))).thenReturn(user1);
        return user1;
    }

    @Test
    public void shouldSaveNewUser_GivenThereExistsOneWithTheSameId_ThenTheExceptionShouldBeThrown() throws Exception {
    	when(userRepository.findOne(String.valueOf(user1.getUserId()))).thenReturn(user1);
        try {
            userService.saveUser(user1);
            fail("Expected exception");
        } catch (EsUserAlreadyExistsException ignored) {
        }
        verify(userRepository, never()).save(any(EsUserEntity.class));
    }

    @Test
    public void shouldListAllUsers_GivenThereExistSome_ThenTheCollectionShouldBeReturned() throws Exception {
    	when(userRepository.findAll()).thenReturn(userList2);
    	Collection<EsUserEntity> list = userService.getAllUsers();
        assertNotNull(list);
        assertEquals(2, list.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    public void shouldListAllUsers_GivenThereNoneExist_ThenTheEmptyCollectionShouldBeReturned() throws Exception {
        when(userRepository.findAll()).thenReturn(new ArrayList<EsUserEntity>());
        Collection<EsUserEntity> list = userService.getAllUsers();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        verify(userRepository, times(1)).findAll();
    }

}
