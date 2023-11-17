package tpe.userTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tpe.userMS.DTO.DTOAccountResponse;
import tpe.userMS.DTO.DTOAccountUserStatusResponse;
import tpe.userMS.DTO.DTOScooterResponse;
import tpe.userMS.DTO.DTOUserRequest;
import tpe.userMS.DTO.DTOUserResponse;
import tpe.userMS.DTO.DTOUserStatusRequest;
import tpe.userMS.controller.UserController;
import tpe.userMS.exception.AccountWithoutMoneyException;
import tpe.userMS.exception.DisabledUserException;
import tpe.userMS.exception.InvalidRolesRequestException;
import tpe.userMS.exception.NotFoundException;
import tpe.userMS.exception.UserWithEmailAlreadyExistsException;
import tpe.userMS.model.Account;
import tpe.userMS.model.Role;
import tpe.userMS.model.User;
import tpe.userMS.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

	 	@Mock
	    private UserService userService;

	    @InjectMocks
	    private UserController userController;

	    
	    @Test
	    void testGetNearbyScooters() {
	        double latitude = 40.7128;
	        double longitude = -74.0060;

	        List<DTOScooterResponse> scooterResponses = new ArrayList<>();
	       
	        when(userService.getNearbyScooters(latitude, longitude)).thenReturn(scooterResponses);

	        ResponseEntity<List<DTOScooterResponse>> responseEntity = userController.getNearbyScooters(latitude, longitude);

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(scooterResponses, responseEntity.getBody());
	    }

	    @Test
	    void testGetUserAccounts() throws NotFoundException {
	        long userId = 1;

	        List<DTOAccountResponse> accountResponses = new ArrayList<>();

	        when(userService.getUserAccounts(userId)).thenReturn(accountResponses);

	        ResponseEntity<List<DTOAccountResponse>> responseEntity = userController.getUserAccounts(userId);

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(accountResponses, responseEntity.getBody());
	    }
	    
	    @Test
	    void testGetAccountByUserIdWithBalance() throws NotFoundException, AccountWithoutMoneyException {
	        long userId = 1;
	        Account mockAccount = new Account(1,100);
	        User mockUser = new User(userId, 223581296,"agustingarciaamaro@gmail.com","31243124", "Agustin", "Garcia","garcia", new ArrayList<>());
	        DTOAccountUserStatusResponse accountUserStatusResponse = new DTOAccountUserStatusResponse(mockAccount, mockUser);
	        
	        when(userService.getAccountByUserIdWithBalance(userId)).thenReturn(accountUserStatusResponse);

	        ResponseEntity<DTOAccountUserStatusResponse> responseEntity = userController.getAccountByUserIdWithBalance(userId);

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(accountUserStatusResponse, responseEntity.getBody());
	    }
	    
	    @Test
	    void testGetUsers() {
	        List<DTOUserResponse> expectedUsers = new ArrayList<>();
	        User mockUser = new User(1, 223581296,"agustingarciaamaro@gmail.com","31243124", "Agustin", "Garcia","garcia", new ArrayList<>());
	        DTOUserResponse expectUser1 = new DTOUserResponse(mockUser);
	        expectedUsers.add(expectUser1);
	        
	        when(userService.findAll()).thenReturn(expectedUsers);

	        ResponseEntity<List<DTOUserResponse>> responseEntity = userController.getUsers();

	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(expectedUsers, responseEntity.getBody()); 
	    }
	    
	    @Test
	    void testSaveUser() throws UserWithEmailAlreadyExistsException, InvalidRolesRequestException {
	    	User mockUser = new User(1, 223581296,"agustingarciaamaro@gmail.com","31243124", "Agustin", "Garcia","garcia", new ArrayList<>());
	        DTOUserRequest userRequest = new DTOUserRequest(1, 223581296,"agustingarciaamaro@gmail.com","31243124", "Agustin", "Garcia","garcia", new ArrayList<>());
	        DTOUserResponse expectedUserResponse = new DTOUserResponse(mockUser);

	        try {
				when(userService.save(any(DTOUserRequest.class))).thenReturn(expectedUserResponse);
			} catch (UserWithEmailAlreadyExistsException e) {
				e.printStackTrace();
			} catch (tpe.userMS.exception.InvalidRolesRequestException e) {
				e.printStackTrace();
			}

	        ResponseEntity<DTOUserResponse> responseEntity = null;
			try {
				responseEntity = userController.saveUser(userRequest);
			} catch (UserWithEmailAlreadyExistsException e) {
				e.printStackTrace();
			} catch (tpe.userMS.exception.InvalidRolesRequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


	        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	        assertEquals(expectedUserResponse, responseEntity.getBody());
	    }
	    
	    @Test
	    public void testDeleteUser() throws NotFoundException {
	        long userId = 123;

	        doNothing().when(userService).delete(userId);

	        ResponseEntity<String> response = userController.deleteUser(userId);

	        verify(userService, times(1)).delete(userId);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals("The user with id " + userId  +" has been succesfully deleted.", response.getBody());
	    }
	    
	    @Test
	    public void testUpdateUserStatus_Success() throws NotFoundException {
	        long userId = 123; 
	        DTOUserStatusRequest statusRequest = new DTOUserStatusRequest(1);
	        User mockUser = new User(userId, 223581296,"agustingarciaamaro@gmail.com","31243124", "Agustin", "Garcia","garcia", new ArrayList<>());
	        DTOUserResponse updatedUserResponse = new DTOUserResponse(mockUser);
	        when(userService.updateStatus(userId, statusRequest)).thenReturn(updatedUserResponse);

	        ResponseEntity<DTOUserResponse> response = userController.updateUserStatus(userId, statusRequest);

	        verify(userService, times(1)).updateStatus(userId, statusRequest);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(updatedUserResponse, response.getBody());
	    }
	    
	    @Test
	    public void testAddAccountToUser_Success() throws NotFoundException, DisabledUserException {
	        Long userId = 123L;
	        Long accountId = 456L;
	        String expectedMessage = "Added account " + accountId + " to user " + userId;

	        doNothing().when(userService).addAccountToUser(userId, accountId);

	        ResponseEntity<String> response = userController.addAccountToUser(userId, accountId);

	        verify(userService, times(1)).addAccountToUser(userId, accountId);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedMessage, response.getBody());
	    }
	    
	    @Test
	    public void testGetUserById_Success() throws NotFoundException {
	        Long userId = 123L;
	        User mockUser = new User(userId, 223581296,"agustingarciaamaro@gmail.com","31243124", "Agustin", "Garcia","garcia", new ArrayList<>());
	        DTOUserResponse expectedUserResponse = new DTOUserResponse(mockUser);

	        when(userService.getUserById(userId)).thenReturn(expectedUserResponse);

	        ResponseEntity<DTOUserResponse> response = userController.getUserById(userId);

	        verify(userService, times(1)).getUserById(userId);

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedUserResponse, response.getBody());
	    }
	    
	    @Test
	    public void testGetUsersBySimpleOrdering_Success() {
	    	User mockUser = new User(1, 223581296,"agustingarciaamaro@gmail.com","31243124", "Agustin", "Garcia","garcia", new ArrayList<>());
	    	User mockUser2 = new User(2, 22358457,"test@gmail.com","31243124", "test", "test","test", new ArrayList<>());
	        List<DTOUserResponse> expectedUsersResponse = Arrays.asList(
	            new DTOUserResponse(mockUser),
	            new DTOUserResponse(mockUser2)
	        );

	        when(userService.getUsersBySimpleOrdering()).thenReturn(expectedUsersResponse);

	        ResponseEntity<List<DTOUserResponse>> response = userController.getUsersBySimpleOrdering();

	        verify(userService, times(1)).getUsersBySimpleOrdering();

	        assertEquals(HttpStatus.OK, response.getStatusCode());
	        assertEquals(expectedUsersResponse, response.getBody());
	    }

}
