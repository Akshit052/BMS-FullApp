package com.teamb.bankmanagementsystem.repository;

import com.teamb.bankmanagementsystem.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
}
