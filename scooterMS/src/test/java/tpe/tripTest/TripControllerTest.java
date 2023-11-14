package tpe.tripTest;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import tpe.scooterMS.DTO.DTOInvoicedAmountResponse;
import tpe.scooterMS.DTO.TripRequestDTO;
import tpe.scooterMS.DTO.TripResponseDTO;
import tpe.scooterMS.controller.TripController;
import tpe.scooterMS.exception.AccountWithoutMoneyException;
import tpe.scooterMS.exception.DisabledUserException;
import tpe.scooterMS.exception.NotFoundException;
import tpe.scooterMS.exception.ScooterNotLocatedAtStopException;
import tpe.scooterMS.exception.ScooterUnavailableException;
import tpe.scooterMS.exception.TripAlreadyEndedException;
import tpe.scooterMS.exception.TripInPauseException;
import tpe.scooterMS.exception.TripNotPausedException;
import tpe.scooterMS.exception.TripReachedPauseTimeLimitException;
import tpe.scooterMS.exception.UserOnTripException;
import tpe.scooterMS.service.TripService;

@ExtendWith(MockitoExtension.class)
public class TripControllerTest {

    @Mock
    private TripService service;

    @InjectMocks
    private TripController controller;

    @Test
    public void testEndPause() throws NotFoundException, TripNotPausedException, TripAlreadyEndedException {
        int tripId = 1;
        TripResponseDTO tripResponse = new TripResponseDTO(tripId, 123, 456, 10.0f, 5.0f, new Date(), null);

        when(service.endPause(tripId)).thenReturn(tripResponse);


        ResponseEntity<TripResponseDTO> responseEntity = controller.endPause(tripId);


        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tripResponse, responseEntity.getBody());


        verify(service, times(1)).endPause(tripId);
    }


    @Test
    public void testFindAll() {
        // Arrange
        List<TripResponseDTO> tripList = Arrays.asList(
                new TripResponseDTO(1, 123, 456, 10.0f, 5.0f, new Date(), null),
                new TripResponseDTO(2, 456, 789, 15.0f, 7.0f, new Date(), null)
        );

        when(service.findAll()).thenReturn(tripList);

        ResponseEntity<List<TripResponseDTO>> responseEntity = controller.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tripList, responseEntity.getBody());

        verify(service, times(1)).findAll();
    }
    
    @Test
    public void testFindById() throws NotFoundException {
        int tripId = 1;
        TripResponseDTO tripResponse = new TripResponseDTO(tripId, 1, 1, 10.0f, 5.0f, null, null);

        when(service.findByid(tripId)).thenReturn(tripResponse);

        ResponseEntity<TripResponseDTO> responseEntity = controller.findById(tripId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tripResponse, responseEntity.getBody());

        verify(service, times(1)).findByid(tripId);
    }
    
    @Test
    public void testSaveTrip() throws NotFoundException, AccountWithoutMoneyException, ScooterUnavailableException, UserOnTripException, DisabledUserException {
        TripRequestDTO requestDTO = new TripRequestDTO(1, 1, 1);
        TripResponseDTO tripResponse = new TripResponseDTO(1, 1, 1, 0.0f, 0.0f, null, null);

        when(service.saveTrip(requestDTO)).thenReturn(tripResponse);

        ResponseEntity<TripResponseDTO> responseEntity = controller.saveTrip(requestDTO);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(tripResponse, responseEntity.getBody());

        verify(service, times(1)).saveTrip(requestDTO);
    }
    
    @Test
    public void testDeleteTrip() throws NotFoundException {
        int tripId = 1;
        TripResponseDTO tripResponse = new TripResponseDTO(tripId, 1, 1, 10.0f, 5.0f, null, null);

        when(service.deleteTrip(tripId)).thenReturn(tripResponse);

        ResponseEntity<TripResponseDTO> responseEntity = controller.deleteTrip(tripId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tripResponse, responseEntity.getBody());

        verify(service, times(1)).deleteTrip(tripId);
    }

    @Test
    public void testEndTrip() throws NotFoundException, TripAlreadyEndedException, ScooterNotLocatedAtStopException {
        int tripId = 1;
        TripResponseDTO tripResponse = new TripResponseDTO(tripId, 1, 1, 10.0f, 5.0f, null, null);

        when(service.endTrip(tripId)).thenReturn(tripResponse);

        ResponseEntity<TripResponseDTO> responseEntity = controller.endTrip(tripId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tripResponse, responseEntity.getBody());

        verify(service, times(1)).endTrip(tripId);
    }
    
    @Test
    public void testGetInvoicedAmountByYearAndMonthRange() {
        int year = 2023;
        int month1 = 1;
        int month2 = 3;
        float invoicedAmount = 100.0f;
        DTOInvoicedAmountResponse responseDTO = new DTOInvoicedAmountResponse(year, month1, month2, invoicedAmount);

        when(service.getInvoicedAmountByYearAndMonthRange(year, month1, month2)).thenReturn(responseDTO);

        ResponseEntity<?> responseEntity = controller.getInvoicedAmountByYearAndMonthRange(year, month1, month2);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(responseDTO, responseEntity.getBody());

        verify(service, times(1)).getInvoicedAmountByYearAndMonthRange(year, month1, month2);
    }
    
    @Test
    public void testStartPause() throws NotFoundException, TripInPauseException, TripAlreadyEndedException, TripReachedPauseTimeLimitException {
        int tripId = 1;
        TripResponseDTO tripResponseDTO = new TripResponseDTO(1,1,1,10,10.0f,null,null);

        when(service.startPause(tripId)).thenReturn(tripResponseDTO);

        ResponseEntity<TripResponseDTO> responseEntity = controller.startPause(tripId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(tripResponseDTO, responseEntity.getBody());

        verify(service, times(1)).startPause(tripId);
    }
}
