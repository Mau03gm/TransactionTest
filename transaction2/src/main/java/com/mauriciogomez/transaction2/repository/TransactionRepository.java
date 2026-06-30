package com.mauriciogomez.transaction2.repository;

import com.mauriciogomez.transaction2.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository
        extends JpaRepository<TransactionEntity, Long> {

}
