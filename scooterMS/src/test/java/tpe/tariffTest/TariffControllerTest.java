package tpe.tariffTest;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tpe.scooterMS.DTO.TariffRequestDTO;
import tpe.scooterMS.DTO.TariffResponseDTO;
import tpe.scooterMS.controller.TariffController;
import tpe.scooterMS.exception.NotFoundException;
import tpe.scooterMS.service.TariffService;
@ExtendWith(MockitoExtension.class)
public class TariffControllerTest {
	@Mock
    private TariffService service;

    @InjectMocks
    private TariffController controller;

    @Test
    public void testFindAll() {
        // Arrange
        List<TariffResponseDTO> tariffList = Arrays.asList(
                new TariffResponseDTO(1, 10.0f, 5.0f, new Date()),
                new TariffResponseDTO(2, 15.0f, 7.0f, new Date())
                // Agrega más elementos según sea necesario
        );

        // Configura el comportamiento del servicio al llamar al método findAll
        when(service.findAll()).thenReturn(tariffList);

        // Act
        ResponseEntity<List<TariffResponseDTO>> responseEntity = controller.findAll();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tariffList, responseEntity.getBody());

        // Verifica que el servicio findAll fue invocado exactamente una vez
        verify(service, times(1)).findAll();
    }

    @Test
    public void testFindById() throws NotFoundException {
        // Arrange
        int tariffId = 1;
        TariffResponseDTO tariffResponse = new TariffResponseDTO(tariffId, 10.0f, 5.0f, new Date());

        // Configura el comportamiento del servicio al llamar al método findById
        when(service.findById(tariffId)).thenReturn(tariffResponse);

        // Act
        ResponseEntity<TariffResponseDTO> responseEntity = controller.findById(tariffId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tariffResponse, responseEntity.getBody());

        // Verifica que el servicio findById fue invocado exactamente una vez con el ID proporcionado
        verify(service, times(1)).findById(tariffId);
    }

    @Test
    public void testSaveTariff() {
        // Arrange
        TariffRequestDTO requestDTO = new TariffRequestDTO(10.0f, 5.0f, new Date());
        TariffResponseDTO savedTariffResponse = new TariffResponseDTO(1, 10.0f, 5.0f, new Date());

        // Configura el comportamiento del servicio al llamar al método saveTariff
        when(service.saveTariff(requestDTO)).thenReturn(savedTariffResponse);

        // Act
        ResponseEntity<TariffResponseDTO> responseEntity = controller.saveTariff(requestDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(savedTariffResponse, responseEntity.getBody());

        // Verifica que el servicio saveTariff fue invocado exactamente una vez con la solicitud proporcionada
        verify(service, times(1)).saveTariff(requestDTO);
    }

    @Test
    public void testDeleteTariff() throws NotFoundException {
        // Arrange
        int tariffId = 1;
        TariffResponseDTO deletedTariffResponse = new TariffResponseDTO(tariffId, 10.0f, 5.0f, new Date());

        // Configura el comportamiento del servicio al llamar al método deleteTariff
        when(service.deleteTariff(tariffId)).thenReturn(deletedTariffResponse);

        // Act
        ResponseEntity<TariffResponseDTO> responseEntity = controller.deleteTariff(tariffId);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(deletedTariffResponse, responseEntity.getBody());

        // Verifica que el servicio deleteTariff fue invocado exactamente una vez con el ID proporcionado
        verify(service, times(1)).deleteTariff(tariffId);
    }
}
