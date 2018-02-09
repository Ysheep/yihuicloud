package com.qzdsoft.eshop.domain.quartz;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="quartz_config")
public class Config {

	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;  

    @Column  
    private String cron;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}  
    
    
}
