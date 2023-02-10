package com.lchalela.consumer.repositoy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lchalela.consumer.model.AsyncTransaction;

@Repository
public interface AsyncTransactionRepository extends JpaRepository<AsyncTransaction, Long>{

}
