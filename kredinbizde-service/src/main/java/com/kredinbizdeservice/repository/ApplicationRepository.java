package com.kredinbizdeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kredinbizdeservice.entity.Application;

public interface ApplicationRepository extends JpaRepository<Application, Long>{

}
