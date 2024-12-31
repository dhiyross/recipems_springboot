package dev.rossy.transactionms.transaction;

//import dev.rossy.recipems.recipe.RecipeResponseDTO;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
//
//@Service
//public class  TransactionService {
//
//    @Autowired
//    private TransactionRepository transactionRepository;
//
//    @Autowired
//    private RecipeRepository recipeRepository;
//
//    public List<TransactionResponseDTO> getAllTransactions() {
//        return transactionRepository.findAll().stream().map(transaction -> {
//            TransactionResponseDTO response = new TransactionResponseDTO();
//            response.setId(transaction.getId());
//            response.setRecipeName(transaction.getRecipe().getName());
//            response.setQuantity(transaction.getQuantity());
//            response.setTotalPrice(transaction.getTotalPrice());
//            response.setTransactionDate(transaction.getTransactionDate());
//            response.setCustomerId(transaction.getCustomerId());
//            return response;
//        }).collect(Collectors.toList());
//    }
//
//    public TransactionResponseDTO createTransaction(TransactionRequestDTO request) {
//        Recipe recipe = recipeRepository.findById(request.getRecipeId())
//                .orElseThrow(() -> new RuntimeException("Recipe not found"));
//
//        Transaction transaction = new Transaction();
//        transaction.setRecipe(recipe);
//        transaction.setQuantity(request.getQuantity());
//        transaction.setTotalPrice(request.getQuantity() * recipe.getPrice());
//        transaction.setTransactionDate(LocalDateTime.now());
//        transaction.setCustomerId(request.getCustomerId()); // Set Customer ID
//
//        Transaction savedTransaction = transactionRepository.save(transaction);
//
//        TransactionResponseDTO response = new TransactionResponseDTO();
//        response.setId(savedTransaction.getId());
//        response.setRecipeName(recipe.getName());
//        response.setQuantity(savedTransaction.getQuantity());
//        response.setTotalPrice(savedTransaction.getTotalPrice());
//        response.setTransactionDate(savedTransaction.getTransactionDate());
//        response.setCustomerId(savedTransaction.getCustomerId());
//
//        return response;
//    }
//
//    public TransactionResponseDTO updateTransaction(Integer transactionId, Integer newQuantity) {
//        Transaction transaction = transactionRepository.findById(transactionId)
//                .orElseThrow(() -> new RuntimeException("Transaction not found"));
//
//        transaction.setQuantity(newQuantity);
//        transaction.setTotalPrice(newQuantity * transaction.getRecipe().getPrice());
//        transactionRepository.save(transaction);
//
//        TransactionResponseDTO response = new TransactionResponseDTO();
//        response.setId(transaction.getId());
//        response.setRecipeName(transaction.getRecipe().getName());
//        response.setQuantity(transaction.getQuantity());
//        response.setTotalPrice(transaction.getTotalPrice());
//        response.setTransactionDate(transaction.getTransactionDate());
//        response.setCustomerId(transaction.getCustomerId());
//
//        return response;
//    }
//}

//@Service
//public class TransactionService {
//
//    @Autowired
//    private TransactionRepository transactionRepository;
//
//    @Value("${recipe.service.url}")
//    private String recipeServiceUrl;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    public TransactionResponseDTO createTransaction(TransactionRequestDTO request) {
//        RecipeResponseDTO recipe = restTemplate.getForObject(recipeServiceUrl + "/api/recipes/" + request.getRecipeId(), RecipeResponseDTO.class);
//
//        if (recipe == null) {
//            throw new RuntimeException("Recipe not found");
//        }
//
//        Transaction transaction = new Transaction();
//        transaction.setRecipeId(request.getRecipeId());
//        transaction.setQuantity(request.getQuantity());
//        transaction.setTotalPrice(request.getQuantity() * recipe.getPrice());
//        transaction.setTransactionDate(LocalDateTime.now());
//        transaction.setCustomerId(request.getCustomerId());
//
//        Transaction savedTransaction = transactionRepository.save(transaction);
//
//        TransactionResponseDTO response = new TransactionResponseDTO();
//        response.setId(savedTransaction.getId());
//        response.setRecipeName(recipe.getName());
//        response.setQuantity(savedTransaction.getQuantity());
//        response.setTotalPrice(savedTransaction.getTotalPrice());
//        response.setTransactionDate(savedTransaction.getTransactionDate());
//        response.setCustomerId(savedTransaction.getCustomerId());
//
//        return response;
//    }
//}

//package com.example.transactionservice.service;

//import com.example.transactionservice.dto.RecipeResponseDTO;
//import com.example.transactionservice.dto.TransactionRequestDTO;
//import com.example.transactionservice.dto.TransactionResponseDTO;
//import com.example.transactionservice.model.Transaction;
//import com.example.transactionservice.repository.TransactionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;

import dev.rossy.recipems.recipe.RecipeResponseDTO;
import dev.rossy.transactionms.transaction.TransactionRequestDTO;
import dev.rossy.transactionms.transaction.TransactionResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Value("${recipe.service.url}")
    private String recipeServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Membuat transaksi baru.
     *
     * @param request DTO permintaan transaksi
     * @return DTO respons transaksi
     */
    public TransactionResponseDTO createTransaction(TransactionRequestDTO request) {
        // Mengambil data resep dari Recipe Service
        RecipeResponseDTO recipe = restTemplate.getForObject(
                recipeServiceUrl + "/api/recipes/" + request.getRecipeId(),
                RecipeResponseDTO.class
        );

        if (recipe == null) {
            throw new RuntimeException("Recipe not found");
        }

        // Membuat objek transaksi
        Transaction transaction = new Transaction();
        transaction.setRecipeId(request.getRecipeId());
        transaction.setQuantity(request.getQuantity());
        transaction.setTotalPrice(request.getQuantity() * recipe.getPrice());
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setCustomerId(request.getCustomerId());

        // Menyimpan transaksi ke database
        Transaction savedTransaction = transactionRepository.save(transaction);

        // Membuat respons transaksi
        TransactionResponseDTO response = new TransactionResponseDTO();
        response.setId(savedTransaction.getId());
        response.setRecipeName(recipe.getName());
        response.setQuantity(savedTransaction.getQuantity());
        response.setTotalPrice(savedTransaction.getTotalPrice());
        response.setTransactionDate(savedTransaction.getTransactionDate());
        response.setCustomerId(savedTransaction.getCustomerId());

        return response;
    }

    /**
     * Mendapatkan semua transaksi.
     *
     * @return Daftar DTO respons transaksi
     */
    public List<TransactionResponseDTO> getAllTransactions() {
        return transactionRepository.findAll().stream().map(transaction -> {
            // Mengambil data resep untuk setiap transaksi
            RecipeResponseDTO recipe = restTemplate.getForObject(
                    recipeServiceUrl + "/api/recipes/" + transaction.getRecipeId(),
                    RecipeResponseDTO.class
            );

            // Membuat DTO respons transaksi
            TransactionResponseDTO response = new TransactionResponseDTO();
            response.setId(transaction.getId());
            response.setRecipeName(recipe != null ? recipe.getName() : "Unknown");
            response.setQuantity(transaction.getQuantity());
            response.setTotalPrice(transaction.getTotalPrice());
            response.setTransactionDate(transaction.getTransactionDate());
            response.setCustomerId(transaction.getCustomerId());

            return response;
        }).collect(Collectors.toList());
    }

    /**
     * Menghapus transaksi berdasarkan ID.
     *
     * @param id ID transaksi
     */
    public void deleteTransaction(Integer id) {
        transactionRepository.deleteById(id);
    }
}
