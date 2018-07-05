package com.skill.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import com.skill.model.AssociateSkillsEntity;

public interface AssociateSkillRepository extends CrudRepository<AssociateSkillsEntity, String> {
	
	List<AssociateSkillsEntity> findByEmpId(String associateId);
	
	
	@Modifying
	 @Transactional
	void deleteByEmpId(String associateId);

}