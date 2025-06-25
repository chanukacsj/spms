package com.example.paymentservice.api;

import com.example.paymentservice.dto.TransactionDTO;
import com.example.paymentservice.entity.Transaction;
import com.example.paymentservice.service.TransactionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/payments")
public class TransactionController {

    @Autowired
    private TransactionService service;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public Transaction makePayment(@RequestBody TransactionDTO dto) {
        return service.makePayment(dto);
    }

//    @GetMapping("/user/{userId}")
//    public List<Transaction> getUserTransactions(@PathVariable Long userId) {
//        return service.getUserTransactions(userId);
//    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('OWNER')")
    public List<Transaction> getAllPayments() {
        return service.getAllPayments();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('OWNER')")
    public Transaction getPaymentById(@PathVariable Long id) {
        return service.getPaymentById(id);
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('USER')")
    public List<Transaction> getPaymentsByUser(@PathVariable Long userId, HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        System.out.println("Token: " + token); // optional logging
        return service.getPaymentsByUserId(userId);
    }
}
