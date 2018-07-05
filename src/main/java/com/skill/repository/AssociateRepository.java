package com.skill.repository;

import org.springframework.data.repository.CrudRepository;

import com.skill.model.EmployeeEntity;

public interface AssociateRepository extends CrudRepository<EmployeeEntity, String> {

}
