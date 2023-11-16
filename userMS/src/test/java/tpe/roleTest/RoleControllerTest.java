package tpe.roleTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tpe.userMS.DTO.DTORoleRequest;
import tpe.userMS.DTO.DTORoleResponse;
import tpe.userMS.controller.RoleController;
import tpe.userMS.exception.NotFoundException;
import tpe.userMS.exception.RoleWithNameAlreadyExistsException;
import tpe.userMS.service.RoleService;

public class RoleControllerTest {

    @Mock
    private RoleService roleService;

    @InjectMocks
    private RoleController roleController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAllRoles() {
        List<DTORoleResponse> mockResponse = Collections.singletonList(new DTORoleResponse(1L, "ROLE_NAME"));
        when(roleService.findAll()).thenReturn(mockResponse);

        ResponseEntity<List<DTORoleResponse>> responseEntity = roleController.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }

    @Test
    public void testFindRoleById() throws NotFoundException {
        DTORoleResponse mockResponse = new DTORoleResponse(1L, "ROLE_NAME");
        when(roleService.findById(1L)).thenReturn(mockResponse);

        ResponseEntity<DTORoleResponse> responseEntity = roleController.findById(1L);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }

    @Test
    public void testSaveRole() throws RoleWithNameAlreadyExistsException {
        DTORoleRequest request = new DTORoleRequest(1L, "ROLE_NAME");
        DTORoleResponse mockResponse = new DTORoleResponse(1L, "ROLE_NAME");
        when(roleService.saveRole(any(DTORoleRequest.class))).thenReturn(mockResponse);

        ResponseEntity<DTORoleResponse> responseEntity = roleController.saveRole(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(mockResponse, responseEntity.getBody());
    }
}
