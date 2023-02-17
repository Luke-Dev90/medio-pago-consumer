package com.lchalela.consumer.repositoy;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lchalela.consumer.model.AsyncTransaction;

@Repository
public interface AsyncTransactionRepository extends JpaRepository<AsyncTransaction, Long>{
	@Query("select a from AsyncTransaction a where a.dateAcreditation between :morning and :afternoon")
	List<AsyncTransaction> findByDateAcreditationBetween(
			@Param("morning") LocalDateTime morning,@Param("afternoon") LocalDateTime afternoon);
}
