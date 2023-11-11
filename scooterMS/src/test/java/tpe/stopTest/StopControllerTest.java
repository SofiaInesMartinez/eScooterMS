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
        DTOStopRequest request = new DTOStopRequest(1, 0, 40.0, -74.0); // Ajusta los valores según tus necesidades.
        DTOStopResponse expectedResponse = new DTOStopResponse(1, 40.0, -74.0); // Ajusta los valores según tus necesidades.
        when(service.save(request)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<DTOStopResponse> responseEntity = controller.saveStop(request);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
    
    @Test
    public void testGetStopById() throws NotFoundException {
        long stopId = 1;
        DTOStopResponse expectedResponse = new DTOStopResponse(stopId, 40.0, -74.0); // Ajusta los valores según tus necesidades.
        when(service.getStopById(stopId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<DTOStopResponse> responseEntity = controller.getStopById(stopId);

        // Assert
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

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }
    
    @Test
    public void testDeleteStop() throws NotFoundException {
        long stopId = 1;

        // Configura el comportamiento del servicio al llamar al método deleteStop con el ID proporcionado
        doNothing().when(service).deleteStop(stopId);

        // Act
        ResponseEntity<String> responseEntity = controller.deleteStop(stopId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Stop con ID " + stopId + " eliminada con éxito.", responseEntity.getBody());

        // Verifica que el servicio deleteStop fue invocado exactamente una vez con el ID proporcionado
        verify(service, times(1)).deleteStop(stopId);
    }
    
    @Test
    public void testGetStop() {
        List<DTOStopResponse> stopList = Arrays.asList(
                new DTOStopResponse(1, 40.0, -74.0),
                new DTOStopResponse(2, 41.0, -75.0)
                // Agrega más elementos según sea necesario
        );

        // Configura el comportamiento del servicio al llamar al método findAll
        when(service.findAll()).thenReturn(stopList);

        ResponseEntity<List<DTOStopResponse>> responseEntity = controller.getStop();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(stopList, responseEntity.getBody());

        // Verifica que el servicio findAll fue invocado exactamente una vez
        verify(service, times(1)).findAll();
    }
}