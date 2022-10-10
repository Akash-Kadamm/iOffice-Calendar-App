package com.ioffice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ioffice.model.Login;
import com.ioffice.model.User;
import com.ioffice.repository.LoginRepository;
import com.ioffice.utils.ResponseMessage;

public class LoginServiceTest {
   
	@Mock
	LoginRepository loginRepo ;
	
	@InjectMocks
	LoginService loginService;
	
	public static final String EMAIL="akashkad@cybage.com";
	
	User user;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		 user=new User(1, "Akash Kadam", "akashkad@cybage.com", "ak@123", "user", "7038967693");
	}

	@Test
	public void testUserByEmail_conditionGetUserObject() {
		
		Mockito.when(loginRepo.findByUserEmail(EMAIL)).thenReturn(user);
		assertEquals(user,loginService.userByEmail(EMAIL));
	}
	
	

	@Test
	public void testUserByEmail_conditionNotFoundUserObjectIsNull() {
		when(loginRepo.findByUserEmail(anyString())).thenReturn(null);
	assertEquals(null,loginService.userByEmail(EMAIL));
	}
	
	
	@Test
	public void testIsUserExists_ConditionNullReturn() {
		when(loginRepo.findByUserEmail(anyString())).thenReturn(null);
		assertEquals(true, loginService.isUserExists(EMAIL));
		
	}
	
	@Test
	public void testIsUserExists_ConditionUserObjectReturn() {
		when(loginRepo.findByUserEmail(EMAIL)).thenReturn(user);
		assertEquals(false, loginService.isUserExists(EMAIL));
		
	}
	
	@Test
	public void testloginCheckByEmailAndPassword_ConditionPasswordMattached() {
		when(loginRepo.findByUserEmail(EMAIL)).thenReturn(user);
		Map<String, Object> actual = loginService.loginCheckByEmailAndPassword(new Login(EMAIL, "ak@123"));
		assertEquals(actual.get("user"), user);
	}
	
	@Test
	public void testloginCheckByEmailAndPassword_ConditionPasswordNotMattached() {
		when(loginRepo.findByUserEmail(EMAIL)).thenReturn(user);
		Map<String, Object> actual = loginService.loginCheckByEmailAndPassword(new Login(EMAIL, "ak@1234"));
		assertEquals(actual.get("message"), ResponseMessage.WRONG_PASSWORD.getMessage());
		
	}
	
	@Test
	public void testloginCheckByEmailAndPassword_ConditionUserNotFound() {
		when(loginRepo.findByUserEmail("ak@cybage.com")).thenReturn(null);
		Map<String, Object> actual = loginService.loginCheckByEmailAndPassword(new Login("ak@cybage.com", "ak@1234"));
		assertEquals(actual.get("message"), ResponseMessage.USER_NOT_FOUND.getMessage());
		
	}

}
