package com.lchalela.consumer.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;

import lombok.Data;

@Data
@Entity
@Table(name = "async_transaction")
public class AsyncTransaction implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2522332798325450113L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String accountOrigin;
	@Nullable
	private String aliasDestination;
	@Nullable
	private String cbuDestination;
	private Boolean isProgrammed;
	@CreationTimestamp
	@DateTimeFormat(pattern = "yyyy-mm-dd hh:mm:ss")
	private LocalDateTime dateAcreditation;
	private String reason;
	private BigDecimal amount;
	
}
