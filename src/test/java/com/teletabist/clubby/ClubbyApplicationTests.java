package com.teletabist.clubby;

//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import com.teletabist.clubby.user.models.User;
import com.teletabist.clubby.user.models.UserRepository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ClubbyApplicationTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void testSave(){
        User user = new User();
        
        user.setUsername("test_username");
        User userMock = mock(user.class);
    
		when(userRepository.save(ArgumentMatchers.any(User.class)));
        User result = userService.save(user);
		when(userMock.getId()).thenReturn(1L);

        assertEquals(result.getUsername(), User.getUser());
    }


    /*
	@Before
	public void createUser() throws Exception{
		userRepository = Mockito.mock(UserRepository.class);
		profileService = Mockito.mock(ProfileService.class);

		userService = new UserService(userRepository);
	}

	@Test
	public void whenCreateuserWithValid(){
			CreateuserRequest createuserRequest = CreateuserRequest.builder();
			createuserRequest.setUserID("t1");
			createuserRequest.setPassword("teat1p");
			
			User user = User.builder()
				.id("t1")
				.password("test1p")
				.name("tester")
				.build();

			User user = User.builder()
				.id("t1")
				.password("test1p")
				.name("tester")
				.build();
			Mockito.when(userService.findByUsername("test1")).thenReturn(user);
			Mockito.when(userRepository.save(user)).thenReturn(user);

			userService result = userService;
			Assert.assertEquals(result,);
			Mockito.verify();
	}*////////////////////////////////////////////////////////////////
	



	/*
    @Test
	public void adduser() {
		userService userServiceMock = mock(userService.class);
		when(userServiceMock.retrieveAlluser()).thenReturn(new int[] { 24, 15, 3 });
		SomeBusinessImpl businessImpl = new SomeBusinessImpl(userServiceMock);
		int result = businessImpl.findTheGreatestFromAlluser();
		assertEquals(24, result);
	}
	*/
}
