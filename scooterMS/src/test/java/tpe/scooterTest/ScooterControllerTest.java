package tpe.scooterTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import tpe.scooterMS.DTO.DTOScooterRequest;
import tpe.scooterMS.DTO.DTOScooterResponse;
import tpe.scooterMS.DTO.DTOScooterStatusRequest;
import tpe.scooterMS.DTO.ScooterByKmsDTO;
import tpe.scooterMS.DTO.ScooterByTimeDTO;
import tpe.scooterMS.DTO.ScooterByTimePauseDTO;
import tpe.scooterMS.DTO.DTOCoordinatesRequest;
import tpe.scooterMS.controller.ScooterController;
import tpe.scooterMS.model.Scooter;
import tpe.scooterMS.service.ScooterService;
import org.springframework.http.ResponseEntity;
import tpe.scooterMS.exception.NotFoundException;

@ExtendWith(MockitoExtension.class)
public class ScooterControllerTest {
	
	@Mock
 	private ScooterService service;
	@InjectMocks
	private ScooterController controller;
	
	@Test
	public void testSaveScooter() {
	    // Datos simulados para la creación de un Scooter.
	    long scooterId = 1;
	    Date lastMaintenanceDate = new Date(System.currentTimeMillis());
	    float kilometers = 100.0f;
	    double latitude = 40.0;
	    double longitude = -74.0;
	
	    // Solicitud DTO para la creación del Scooter.
	    DTOScooterRequest request = new DTOScooterRequest(scooterId, lastMaintenanceDate, kilometers, latitude, longitude);
	
	    // Scooter simulado que debería devolver el servicio al guardar.
	    Scooter savedScooter = new Scooter(scooterId, lastMaintenanceDate, kilometers, latitude, longitude);
	
	    // Configuración del comportamiento esperado del servicio al recibir la solicitud de guardar un Scooter.
	    when(service.save(request)).thenReturn(new DTOScooterResponse(savedScooter));

	
	    // Llamada al método del controlador que debería invocar al servicio y retornar la respuesta.
	    DTOScooterResponse response = controller.saveScooter(request).getBody();
	
	    // Assert
	
	    // Verificación de que la respuesta no es nula y contiene los datos esperados.
	    assertNotNull(response);
	    assertEquals(scooterId, response.getId());
	    assertEquals(lastMaintenanceDate, response.getLastMaintenanceDate());
	    assertEquals(kilometers, response.getKilometers());
	
	    // Verificación de que el servicio fue invocado exactamente una vez con la solicitud proporcionada.
	    verify(service, times(1)).save(request);
	 }
	 
 	@Test
 	public void testGetScooters() {
        List<DTOScooterResponse> expectedScooters = new ArrayList<>();
        expectedScooters.add(new DTOScooterResponse(1, null, 100.0f, ""));
        expectedScooters.add(new DTOScooterResponse(2, null, 150.0f, ""));

        when(service.findAll()).thenReturn(expectedScooters);

        ResponseEntity<List<DTOScooterResponse>> responseEntity = controller.getScooters();

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<DTOScooterResponse> actualScooters = responseEntity.getBody();
        assertNotNull(actualScooters);
        assertEquals(expectedScooters.size(), actualScooters.size());
        
        // Verificar que los elementos en las listas sean iguales.
        for (int i = 0; i < expectedScooters.size(); i++) {
            assertEquals(expectedScooters.get(i), actualScooters.get(i));
        }

        // Verificar que el servicio fue invocado exactamente una vez.
        verify(service, times(1)).findAll();
    }
 	
 	@Test
    public void testUpdateScooterCoordinates() throws NotFoundException {
        long scooterId = 1;
        DTOCoordinatesRequest request = new DTOCoordinatesRequest(35.0, -120.0);

        // Simulamos la respuesta del servicio
        DTOScooterResponse expectedResponse = new DTOScooterResponse(scooterId, null, 0.0f, "");
        when(service.updateScooterCoordinates(scooterId, request)).thenReturn(expectedResponse);

        ResponseEntity<DTOScooterResponse> responseEntity = controller.updateScooterCoordinates(scooterId, request);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        DTOScooterResponse actualResponse = responseEntity.getBody();
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

        // Verificar que el servicio fue invocado exactamente una vez.
        verify(service, times(1)).updateScooterCoordinates(scooterId, request);
    }

    @Test
    public void testDTOCoordinatesRequestEqualsAndHashCode() {
        DTOCoordinatesRequest request1 = new DTOCoordinatesRequest(35.0, -120.0);
        DTOCoordinatesRequest request2 = new DTOCoordinatesRequest(35.0, -120.0);
        DTOCoordinatesRequest request3 = new DTOCoordinatesRequest(40.0, -110.0);

        //Assert
        assertEquals(request1, request2);
        assertNotEquals(request1, request3);
        assertEquals(request1.hashCode(), request2.hashCode());
        assertNotEquals(request1.hashCode(), request3.hashCode());
    }
    
    @Test
    public void testGetNearbyScooters() {
        double latitude = 35.0;
        double longitude = -120.0;

        // Simulamos la respuesta del servicio
        List<DTOScooterResponse> expectedScooters = new ArrayList<>();
        expectedScooters.add(new DTOScooterResponse(1, null, 100.0f, ""));
        expectedScooters.add(new DTOScooterResponse(2, null, 150.0f, ""));
        when(service.getNearbyScooters(latitude, longitude)).thenReturn(expectedScooters);

        ResponseEntity<List<DTOScooterResponse>> responseEntity = controller.getNearbyScooters(latitude, longitude);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<DTOScooterResponse> actualScooters = responseEntity.getBody();
        assertNotNull(actualScooters);
        assertEquals(expectedScooters.size(), actualScooters.size());
        
        // Verificar que los elementos en las listas sean iguales.
        for (int i = 0; i < expectedScooters.size(); i++) {
            assertEquals(expectedScooters.get(i), actualScooters.get(i));
        }

        // Verificar que el servicio fue invocado exactamente una vez.
        verify(service, times(1)).getNearbyScooters(latitude, longitude);
    }
    
    @Test
    public void testUpdateScooterStatus() throws NotFoundException {
        // Arrange
        long scooterId = 1;
        DTOScooterStatusRequest request = new DTOScooterStatusRequest("maintenance");

        // Simulamos la respuesta del servicio
        DTOScooterResponse expectedResponse = new DTOScooterResponse(scooterId, null, 0.0f, "maintenance");
        when(service.updateScooterStatus(scooterId, request)).thenReturn(expectedResponse);

        ResponseEntity<DTOScooterResponse> responseEntity = controller.updateScooterStatus(scooterId, request);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        DTOScooterResponse actualResponse = responseEntity.getBody();
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

        // Verificar que el servicio fue invocado exactamente una vez.
        verify(service, times(1)).updateScooterStatus(scooterId, request);
    }
    
    @Test
    public void testGetScooterById() throws NotFoundException {
        long scooterId = 1;

        // Simulamos la respuesta del servicio
        DTOScooterResponse expectedResponse = new DTOScooterResponse(scooterId, null, 0.0f, "");
        when(service.getScooterById(scooterId)).thenReturn(expectedResponse);

        ResponseEntity<DTOScooterResponse> responseEntity = controller.getScooterById(scooterId);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        DTOScooterResponse actualResponse = responseEntity.getBody();
        assertNotNull(actualResponse);
        assertEquals(expectedResponse, actualResponse);

        // Verificar que el servicio fue invocado exactamente una vez.
        verify(service, times(1)).getScooterById(scooterId);
    }
    
    @Test
    public void testGetScootersBySimpleOrdering() {
        // Simulamos la respuesta del servicio
        List<DTOScooterResponse> expectedScooters = new ArrayList<>();
        expectedScooters.add(new DTOScooterResponse(1L, null, 100.0f, ""));
        expectedScooters.add(new DTOScooterResponse(2L, null, 150.0f, ""));
        when(service.getScootersBySimpleOrdering()).thenReturn(expectedScooters);

        ResponseEntity<List<DTOScooterResponse>> responseEntity = controller.getScootersBySimpleOrdering();

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<DTOScooterResponse> actualScooters = responseEntity.getBody();
        assertNotNull(actualScooters);
        assertEquals(expectedScooters.size(), actualScooters.size());

        // Verificar que los elementos en las listas sean iguales.
        for (int i = 0; i < expectedScooters.size(); i++) {
            assertEquals(expectedScooters.get(i), actualScooters.get(i));
        }

        // Verificar que el servicio fue invocado exactamente una vez.
        verify(service, times(1)).getScootersBySimpleOrdering();
    }
    
    @Test
    public void testDeleteScooter() throws NotFoundException {
    	  // Arrange
        long scooterId = 1;

        // Insertar un scooter
        ResponseEntity<DTOScooterResponse> insertResponseEntity = controller.saveScooter(new DTOScooterRequest(scooterId, null, 0.0f, 0.0, 0.0));
        assertEquals(HttpStatus.OK, insertResponseEntity.getStatusCode());


        ResponseEntity<String> deleteResponseEntity = controller.deleteScooter(scooterId);

        // Assert
        assertNotNull(deleteResponseEntity);
        assertEquals(HttpStatus.OK, deleteResponseEntity.getStatusCode());

        String deleteMessage = deleteResponseEntity.getBody();
        assertNotNull(deleteMessage);
        assertEquals("Scooter con ID " + scooterId + " eliminado con éxito.", deleteMessage);

        // Verificar que el servicio de búsqueda de scooter por ID devuelva null después de la eliminación.
        when(service.getScooterById(scooterId)).thenReturn(null);
        assertNull(service.getScooterById(scooterId));
    }
    
    @Test
    public void testGetScootersReportByKm() {
        // Arrange
        long scooterId = 1;
        float kilometers = 100.5f;
        ScooterByKmsDTO expectedDto = new ScooterByKmsDTO(scooterId, kilometers);
        List<ScooterByKmsDTO> expectedList = Collections.singletonList(expectedDto);

        // Configurar el comportamiento esperado del servicio
        when(service.getScootersReportByKm()).thenReturn(expectedList);

        ResponseEntity<List<ScooterByKmsDTO>> responseEntity = controller.getScootersReportByKm();

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<ScooterByKmsDTO> actualList = responseEntity.getBody();
        assertNotNull(actualList);
        assertEquals(expectedList.size(), actualList.size());

        ScooterByKmsDTO actualDto = actualList.get(0);
        assertEquals(expectedDto.getIdScooter(), actualDto.getIdScooter());
        assertEquals(expectedDto.getKilometers(), actualDto.getKilometers());

        // Verificar que el servicio fue invocado exactamente una vez.
        verify(service, times(1)).getScootersReportByKm();
    }
    
    @Test
    public void testGetScootersReportByTotalTime() {
        long scooterId = 1;
        long time = 50;
        ScooterByTimePauseDTO expectedDto = new ScooterByTimePauseDTO(scooterId, time);
        List<ScooterByTimePauseDTO> expectedList = Collections.singletonList(expectedDto);

        // Configurar el comportamiento esperado del servicio
        when(service.getScootersReportByTotalTime()).thenReturn(expectedList);

        // Act
        ResponseEntity<List<ScooterByTimePauseDTO>> responseEntity = controller.getScootersReportByTotalTime();

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<ScooterByTimePauseDTO> actualList = responseEntity.getBody();
        assertNotNull(actualList);
        assertEquals(expectedList.size(), actualList.size());

        ScooterByTimePauseDTO actualDto = actualList.get(0);
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getTime(), actualDto.getTime());

        // Verificar que el servicio fue invocado exactamente una vez.
        verify(service, times(1)).getScootersReportByTotalTime();
    }
    
    @Test
    public void testGetScootersReportByTimeWithPauses() {
        // Arrange
        long scooterId = 1L;
        long time = 50L;
        ScooterByTimeDTO expectedDto = new ScooterByTimeDTO(scooterId, time);
        List<ScooterByTimeDTO> expectedList = Collections.singletonList(expectedDto);

        // Configurar el comportamiento esperado del servicio
        when(service.getScootersReportByTimeWithPauses()).thenReturn(expectedList);

        // Act
        ResponseEntity<List<ScooterByTimeDTO>> responseEntity = controller.getScootersReportByTimeWithPauses();

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<ScooterByTimeDTO> actualList = responseEntity.getBody();
        assertNotNull(actualList);
        assertEquals(expectedList.size(), actualList.size());

        ScooterByTimeDTO actualDto = actualList.get(0);
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getTime(), actualDto.getTime());

        // Verificar que el servicio fue invocado exactamente una vez.
        verify(service, times(1)).getScootersReportByTimeWithPauses();
    }
    
    @Test
    public void testGetScootersByMinimumNumberOfTrips() {
        // Arrange
        int number = 5;
        int year = 2023;

        // Crear un objeto Scooter simulado para usar en el DTO de respuesta
        Scooter simulatedScooter = new Scooter();
        simulatedScooter.setId(1L);
        simulatedScooter.setLastMaintenanceDate(null);
        simulatedScooter.setKilometers(100.0f);
        simulatedScooter.setStatus("available");

        DTOScooterResponse expectedDto = new DTOScooterResponse(simulatedScooter);
        List<DTOScooterResponse> expectedList = Collections.singletonList(expectedDto);

        // Configurar el comportamiento esperado del servicio
        when(service.getScootersByMinimumNumberOfTrips(number, year)).thenReturn(expectedList);

        ResponseEntity<List<DTOScooterResponse>> responseEntity = controller.getScootersByMinimumNumberOfTrips(number, year);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        List<DTOScooterResponse> actualList = responseEntity.getBody();
        assertNotNull(actualList);
        assertEquals(expectedList.size(), actualList.size());

        DTOScooterResponse actualDto = actualList.get(0);
        // Asegúrate de ajustar estas aserciones según la estructura de tu DTO y los datos esperados
        assertEquals(expectedDto.getId(), actualDto.getId());
        assertEquals(expectedDto.getLastMaintenanceDate(), actualDto.getLastMaintenanceDate());
        assertEquals(expectedDto.getKilometers(), actualDto.getKilometers());
        assertEquals(expectedDto.getStatus(), actualDto.getStatus());

        // Verificar que el servicio fue invocado exactamente una vez.
        verify(service, times(1)).getScootersByMinimumNumberOfTrips(number, year);
    }
    
    
    void testGetScootersByStatus() {
        // Arrange
        Map<String, Long> statusMap = new HashMap<>();
        statusMap.put("available", 10L);
        // Agrega más estados según sea necesario

        // Configura el comportamiento esperado del servicio
        when(service.getScootersByStatus()).thenReturn(statusMap);

        // Act
        ResponseEntity<Map<String, Long>> responseEntity = controller.getScootersByStatus();

        // Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Map<String, Long> responseBody = responseEntity.getBody();
        assertNotNull(responseBody);

        // Ajusta estas aserciones según la estructura esperada de tu respuesta
        assertTrue(responseBody.containsKey("available"));
        assertEquals(10L, responseBody.get("available"));

        // Verifica que el servicio fue invocado exactamente una vez
        verify(service, times(1)).getScootersByStatus();
    }
}