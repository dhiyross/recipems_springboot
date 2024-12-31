package dev.rossy.transactionms.Controller;


import dev.rossy.transactionms.transaction.TransactionController;
import dev.rossy.transactionms.transaction.TransactionRequestDTO;
import dev.rossy.transactionms.transaction.TransactionResponseDTO;
import dev.rossy.transactionms.transaction.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTransactions_Status200() {
        // Mock data
        List<TransactionResponseDTO> mockTransactions = new ArrayList<>();
        TransactionResponseDTO transaction = new TransactionResponseDTO();
        transaction.setId(1);
        transaction.setRecipeName("Mock Recipe");
        transaction.setQuantity(2);
        transaction.setTotalPrice(20.0);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setCustomerId(1);
        mockTransactions.add(transaction);

        // Mock behavior
        when(transactionService.getAllTransactions()).thenReturn(mockTransactions);

        // Test
        ResponseEntity<List<TransactionResponseDTO>> response = transactionController.getAllTransactions();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(1, response.getBody().size());
        verify(transactionService, times(1)).getAllTransactions();
    }

    @Test
    void testCreateTransaction_Status201() {
        // Mock data
        TransactionRequestDTO request = new TransactionRequestDTO();
        request.setRecipeId(1);
        request.setQuantity(2);
        request.setCustomerId(1);

        TransactionResponseDTO responseDTO = new TransactionResponseDTO();
        responseDTO.setId(1);
        responseDTO.setRecipeName("Mock Recipe");
        responseDTO.setQuantity(2);
        responseDTO.setTotalPrice(20.0);
        responseDTO.setTransactionDate(LocalDateTime.now());
        responseDTO.setCustomerId(1);

        // Mock behavior
        when(transactionService.createTransaction(request)).thenReturn(responseDTO);

        // Test
        ResponseEntity<TransactionResponseDTO> response = transactionController.createTransaction(request);
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Mock Recipe", response.getBody().getRecipeName());
        verify(transactionService, times(1)).createTransaction(request);
    }



    @Test
    void testCreateTransaction_Status500() {
        // Mock data
        TransactionRequestDTO request = new TransactionRequestDTO();
        request.setRecipeId(1);
        request.setQuantity(2);
        request.setCustomerId(1);

        // Mock behavior
        when(transactionService.createTransaction(request)).thenThrow(new RuntimeException("Internal server error"));

        // Test
        ResponseEntity<TransactionResponseDTO> response = transactionController.createTransaction(request);
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Internal server error: Internal server error", response.getBody().getErrorMessage());
        verify(transactionService, times(1)).createTransaction(request);
    }



}
