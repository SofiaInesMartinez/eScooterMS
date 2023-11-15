package tpe.stopTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tpe.scooterMS.DTO.DTOStopRequest;
import tpe.scooterMS.DTO.DTOStopResponse;
import tpe.scooterMS.controller.StopController;
import tpe.scooterMS.exception.NotFoundException;
import tpe.scooterMS.service.StopService;

@ExtendWith(MockitoExtension.class)
public class StopControllerTest {

    @Mock
    private StopService service;

    @InjectMocks
    private StopController controller;

    @Test
    public void testSaveStop() {
        DTOStopRequest request = new DTOStopRequest(1, 0, 40.0, -74.0); 
        DTOStopResponse expectedResponse = new DTOStopResponse(1, 40.0, -74.0); 
        
        when(service.save(request)).thenReturn(expectedResponse);
        
        ResponseEntity<DTOStopResponse> responseEntity = controller.saveStop(request);
        System.out.println(responseEntity);
        
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
    
    @Test
    public void testGetStopById() throws NotFoundException {
        long stopId = 1;
        DTOStopResponse expectedResponse = new DTOStopResponse(stopId, 40.0, -74.0);
        when(service.getStopById(stopId)).thenReturn(expectedResponse);

        ResponseEntity<DTOStopResponse> responseEntity = controller.getStopById(stopId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
    
    @Test
    public void testGetStopsBySimpleOrdering() {
        List<DTOStopResponse> expectedResponse = Arrays.asList(
                new DTOStopResponse(1, 40.0, -74.0),
                new DTOStopResponse(2, 41.0, -75.0)
        );
        when(service.getStopsBySimpleOrdering()).thenReturn(expectedResponse);

        ResponseEntity<List<DTOStopResponse>> responseEntity = controller.getStopsBySimpleOrdering();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
    
    @Test
    public void testDeleteStop() throws NotFoundException {
        long stopId = 1;

        doNothing().when(service).deleteStop(stopId);

        ResponseEntity<String> responseEntity = controller.deleteStop(stopId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Stop con ID " + stopId + " eliminada con éxito.", responseEntity.getBody());

        verify(service, times(1)).deleteStop(stopId);
    }
    
    @Test
    public void testGetStop() {
        List<DTOStopResponse> stopList = Arrays.asList(
                new DTOStopResponse(1, 40.0, -74.0),
                new DTOStopResponse(2, 41.0, -75.0)
        );


        when(service.findAll()).thenReturn(stopList);

        ResponseEntity<List<DTOStopResponse>> responseEntity = controller.getStop();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(stopList, responseEntity.getBody());

        verify(service, times(1)).findAll();
    }
}