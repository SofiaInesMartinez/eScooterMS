package tpe.accountTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tpe.userMS.DTO.DTOAccountRequest;
import tpe.userMS.DTO.DTOAccountResponse;
import tpe.userMS.DTO.DTOAccountUserStatusResponse;
import tpe.userMS.DTO.DTOReduceBalanceRequest;
import tpe.userMS.controller.AccountController;
import tpe.userMS.exception.AccountWithoutMoneyException;
import tpe.userMS.exception.DisabledUserException;
import tpe.userMS.exception.NotFoundException;
import tpe.userMS.service.AccountService;

public class AccountControllerTest {

    @Mock
    private AccountService accountService;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAccounts() {
        List<DTOAccountResponse> mockResponse = new ArrayList<>();
        mockResponse.add(new DTOAccountResponse(1L, 100));
        mockResponse.add(new DTOAccountResponse(2L, 200));
        mockResponse.add(new DTOAccountResponse(3L, 300));

        when(accountService.findAll()).thenReturn(mockResponse);

        ResponseEntity<List<DTOAccountResponse>> responseEntity = accountController.getAccounts();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }
    
    @Test
    public void testGetAccountByUserIdWithBalance() throws AccountWithoutMoneyException {
        DTOAccountUserStatusResponse mockResponse = new DTOAccountUserStatusResponse(1L, 500, 123L, 1);

        when(accountService.getAccountByUserIdWithBalance(123L)).thenReturn(mockResponse);

        ResponseEntity<DTOAccountUserStatusResponse> responseEntity = accountController.getAccountByUserIdWithBalance(123L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }
    
    @Test
    public void testSaveAccount() {
        DTOAccountRequest mockRequest = new DTOAccountRequest(1L, 1000);
        DTOAccountResponse mockResponse = new DTOAccountResponse(1L, 1000);

        when(accountService.save(mockRequest)).thenReturn(mockResponse);

        ResponseEntity<DTOAccountResponse> responseEntity = accountController.saveAccount(mockRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }
    
    @Test
    public void testDeleteAccount() throws NotFoundException {
        long accountId = 1L;
        
        doNothing().when(accountService).delete(accountId);

        ResponseEntity<String> responseEntity = accountController.deleteAccount(accountId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("The account with id " + accountId + " has been succesfully deleted.", responseEntity.getBody());
    }
    
    @Test
    public void testReduceAccountMoneyBalance_Success() throws NotFoundException {
        long accountId = 1L;
        int reduceAmount = 50;

        DTOReduceBalanceRequest request = new DTOReduceBalanceRequest();
        request.setMoney(reduceAmount);

        doNothing().when(accountService).reduceMoneyBalance(accountId, request);

        ResponseEntity<String> responseEntity = accountController.reduceAccountMoneyBalance(accountId, request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Account with ID " + accountId + " has reduced its balance an amount of " + reduceAmount, responseEntity.getBody());
    }
    
    @Test
    public void testUpdateAccountMoneyBalance_Success() throws NotFoundException {
        long accountId = 1L;
        int newMoneyBalance = 1000;

        doNothing().when(accountService).updateMoneyBalance(accountId, newMoneyBalance);

        ResponseEntity<String> responseEntity = accountController.updateAccountMoneyBalance(accountId, newMoneyBalance);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Account with ID " + accountId + " has been updated money balance to " + newMoneyBalance, responseEntity.getBody());
    }
    
    @Test
    public void testGetAccountById() throws NotFoundException {
        long accountId = 1L;
        DTOAccountResponse accountResponse = new DTOAccountResponse(accountId, 1000);

        when(accountService.getAccountById(accountId)).thenReturn(accountResponse);

        ResponseEntity<DTOAccountResponse> responseEntity = accountController.getAccountById(accountId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(accountId, responseEntity.getBody().getId());
    }

    @Test
    public void testGetAccountsBySimpleOrdering() {
        List<DTOAccountResponse> accounts = new ArrayList<>();
        accounts.add(new DTOAccountResponse(1L, 1000));

        when(accountService.getAccountsBySimpleOrdering()).thenReturn(accounts);

        ResponseEntity<List<DTOAccountResponse>> responseEntity = accountController.getAccountsBySimpleOrdering();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(accounts.size(), responseEntity.getBody().size());
    }

    @Test
    public void testAddUserToAccount() throws NotFoundException, DisabledUserException {
        Long accountId = 1L;
        Long userId = 2L;
        doNothing().when(accountService).addUserToAccount(accountId, userId);

        ResponseEntity<String> responseEntity = accountController.addUserToAccount(accountId, userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Added user " + userId + " to account " + accountId, responseEntity.getBody());
    }
}
