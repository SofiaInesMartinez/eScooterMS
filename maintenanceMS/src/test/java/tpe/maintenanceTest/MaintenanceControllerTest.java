package tpe.maintenanceTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tpe.maintenanceMS.controller.MaintenanceController;
import tpe.maintenanceMS.dto.DTOMaintenanceRequest;
import tpe.maintenanceMS.dto.DTOMaintenanceResponse;
import tpe.maintenanceMS.dto.DTOScooterResponse;
import tpe.maintenanceMS.dto.DTOScooterStatusRequest;
import tpe.maintenanceMS.dto.ScooterByTimeDTO;
import tpe.maintenanceMS.dto.ScooterByTimePauseDTO;
import tpe.maintenanceMS.exception.MaintenanceAlreadyFinishedException;
import tpe.maintenanceMS.exception.NotFoundException;
import tpe.maintenanceMS.exception.ScooterAlreadyInMaintenanceException;
import tpe.maintenanceMS.exception.ServiceCommunicationException;
import tpe.maintenanceMS.model.Maintenance;
import tpe.maintenanceMS.service.MaintenanceService;

public class MaintenanceControllerTest {

	
	private MaintenanceService maintenanceService;
    private MaintenanceController maintenanceController;

    @BeforeEach
    public void setup() {
        maintenanceService = mock(MaintenanceService.class);
        maintenanceController = new MaintenanceController(maintenanceService);
    }
    
    
    @Test
    public void testGetScootersReportByTimeWithPauses() throws ServiceCommunicationException {
        List<ScooterByTimePauseDTO> expectedReport = new ArrayList<>();
        expectedReport.add(new ScooterByTimePauseDTO(1L, 100L));

        when(maintenanceService.getScootersReportByTimeWithPauses()).thenReturn(expectedReport);

        ResponseEntity<?> responseEntity = maintenanceController.getScootersReportByTimeWithPauses();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedReport, responseEntity.getBody());
    }

    @Test
    public void testFinishMaintenance() throws NotFoundException, MaintenanceAlreadyFinishedException, ServiceCommunicationException {
        long maintenanceId = 1L;

        ResponseEntity<?> responseEntity = maintenanceController.finishMaintenance(maintenanceId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void testUpdateScooterStatus() throws NotFoundException {
        long scooterId = 1L;
        DTOScooterStatusRequest request = new DTOScooterStatusRequest("maintenance");
        doReturn(new DTOScooterResponse()).when(maintenanceService).updateScooterStatus(scooterId, request);

        ResponseEntity<?> responseEntity = maintenanceController.updateScooterStatus(scooterId, request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void testGetMaintenance_Success() {
    	Date startDate = new Date(1234567890);
        Date finishDate = new Date(1234567890);
        Maintenance maintenance = new Maintenance(1, "Mantenimiento preventivo", startDate, finishDate, 101);

        List<DTOMaintenanceResponse> maintenanceList = new ArrayList<>();
        maintenanceList.add(new DTOMaintenanceResponse(maintenance));
        when(maintenanceService.findAll()).thenReturn(maintenanceList);

        ResponseEntity<?> responseEntity = maintenanceController.getMaintenance();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    
    @Test
    public void testSaveMaintenance() throws NotFoundException, ScooterAlreadyInMaintenanceException {
    	long scooterId = 101L;
    	long id = 1L;
    	Date startDate = new Date(1234567890);
        Date finishDate = new Date(1234567890);
        Maintenance maintenance = new Maintenance(1, "Mantenimiento preventivo", startDate, finishDate, 101);
        DTOMaintenanceResponse response = new DTOMaintenanceResponse(maintenance);
        DTOMaintenanceRequest request = new DTOMaintenanceRequest(id, "Mantenimiento preventivo", startDate, finishDate, scooterId);
        when(maintenanceService.save(request)).thenReturn(response);
        ResponseEntity<?> responseEntity = maintenanceController.saveMaintenance(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode()); 
    }
    
    @Test
    public void testGetMaintenanceById() throws NotFoundException {
        long maintenanceId = 1L;
        Date startDate = new Date(1234567890);
        Date finishDate = new Date(1234567890);
        Maintenance maintenance = new Maintenance(1, "Mantenimiento preventivo", startDate, finishDate, 101);
        DTOMaintenanceResponse response = new DTOMaintenanceResponse(maintenance);
        when(maintenanceService.getMaintenanceById(maintenanceId)).thenReturn(response);

 
        ResponseEntity<?> responseEntity = maintenanceController.getMaintenanceById(maintenanceId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());
    }
    
    @Test
    public void testGetMaintenancesBySimpleOrdering() {
    	long maintenanceId = 1L;
        Date startDate = new Date(1234567890);
        Date finishDate = new Date(1234567890);
        Maintenance maintenance = new Maintenance(1, "Mantenimiento preventivo", startDate, finishDate, 101);
        DTOMaintenanceResponse response = new DTOMaintenanceResponse(maintenance);
        List<DTOMaintenanceResponse> maintenanceList = new ArrayList<>();
        maintenanceList.add(response);
        when(maintenanceService.getMaintenancesBySimpleOrdering()).thenReturn(maintenanceList);

        ResponseEntity<?> responseEntity = maintenanceController.getMaintenancesBySimpleOrdering();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode()); 
        assertEquals(maintenanceList, responseEntity.getBody());
    }

}
