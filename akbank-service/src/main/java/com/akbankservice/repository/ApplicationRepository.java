package com.akbankservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akbankservice.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>{

}
