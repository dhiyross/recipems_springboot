package dev.rossy.transactionms.transaction;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getAllTransactions() {
        return ResponseEntity.ok(transactionService.getAllTransactions());
    }

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> createTransaction(@Valid @RequestBody TransactionRequestDTO request) {
        try {
            TransactionResponseDTO response = transactionService.createTransaction(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(500).body(new TransactionResponseDTO("Internal server error: " + e.getMessage()));
        }
    }



//    @PutMapping("/{id}/update-quantity")
//    public ResponseEntity<TransactionResponseDTO> updateTransactionQuantity(
//            @PathVariable Integer id,
//            @RequestParam Integer newQuantity) {
//
//        return ResponseEntity.ok(transactionService.updateTransaction(id, newQuantity));
//    }
}

