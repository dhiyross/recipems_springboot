//package dev.rossy.transactionms.Service;
//
//import dev.rossy.transactionms.transaction.TransactionService;
//import dev.rossy.recipe.recipe.RecipeRepository;
//import dev.rossy.recipe.transaction.*;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class TransactionServiceTest {
//
//    @Mock
//    private TransactionRepository transactionRepository;
//
//    @Mock
//    private RecipeRepository recipeRepository;
//
//    @InjectMocks
//    private TransactionService transactionService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void getAllTransactions_Returns200() {
//        // Arrange
//        List<Transaction> transactions = new ArrayList<>();
//        Transaction transaction = new Transaction();
//        transaction.setId(1);
//        transaction.setRecipe(new Recipe());
//        transaction.setQuantity(2);
//        transaction.setTotalPrice(50.0);
//        transaction.setTransactionDate(LocalDateTime.now());
//        transaction.setCustomerId(1001);
//        transactions.add(transaction);
//
//        when(transactionRepository.findAll()).thenReturn(transactions);
//
//        // Act
//        List<TransactionResponseDTO> response = transactionService.getAllTransactions();
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(1, response.size());
//        verify(transactionRepository, times(1)).findAll();
//    }
//
//    @Test
//    void createTransaction_Returns201() {
//        // Arrange
//        Recipe recipe = new Recipe();
//        recipe.setId(1);
//        recipe.setPrice(25.0);
//        recipe.setName("Sample Recipe");
//
//        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
//        requestDTO.setRecipeId(1);
//        requestDTO.setQuantity(2);
//        requestDTO.setCustomerId(1001);
//
//        Transaction transaction = new Transaction();
//        transaction.setId(1);
//        transaction.setRecipe(recipe);
//        transaction.setQuantity(2);
//        transaction.setTotalPrice(50.0);
//        transaction.setTransactionDate(LocalDateTime.now());
//        transaction.setCustomerId(1001);
//
//        when(recipeRepository.findById(1)).thenReturn(Optional.of(recipe));
//        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);
//
//        // Act
//        TransactionResponseDTO response = transactionService.createTransaction(requestDTO);
//
//        // Assert
//        assertNotNull(response);
//        assertEquals(1, response.getId());
//        assertEquals("Sample Recipe", response.getRecipeName());
//        verify(recipeRepository, times(1)).findById(1);
//        verify(transactionRepository, times(1)).save(any(Transaction.class));
//    }
//
//    @Test
//    void createTransaction_Returns404WhenRecipeNotFound() {
//        // Arrange
//        TransactionRequestDTO requestDTO = new TransactionRequestDTO();
//        requestDTO.setRecipeId(1);
//
//        when(recipeRepository.findById(1)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class,
//                () -> transactionService.createTransaction(requestDTO));
//        assertEquals("Recipe not found", exception.getMessage());
//        verify(recipeRepository, times(1)).findById(1);
//        verify(transactionRepository, never()).save(any(Transaction.class));
//    }
//
//    @Test
//    void updateTransaction_Returns500WhenTransactionNotFound() {
//        // Arrange
//        Integer transactionId = 1;
//        Integer newQuantity = 5;
//
//        when(transactionRepository.findById(transactionId)).thenReturn(Optional.empty());
//
//        // Act & Assert
//        RuntimeException exception = assertThrows(RuntimeException.class,
//                () -> transactionService.updateTransaction(transactionId, newQuantity));
//        assertEquals("Transaction not found", exception.getMessage());
//        verify(transactionRepository, times(1)).findById(transactionId);
//        verify(transactionRepository, never()).save(any(Transaction.class));
//    }
//}
