package com.mauriciogomez.transaction2.repository;

import com.mauriciogomez.transaction2.entity.TransactionEntity;
import feign.Param;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository
        extends JpaRepository<TransactionEntity, Long> {

    @Modifying
    @Transactional
    @Query("""
       UPDATE TransactionEntity t
       SET t.estatus = :estatus
       WHERE t.id = :id
       AND t.referencia = :referencia
       """)
    int updateStatus(
            @Param("id") Long id,
            @Param("referencia") String referencia,
            @Param("estatus") String estatus
    );

}
