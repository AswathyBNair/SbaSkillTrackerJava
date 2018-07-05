package com.skill.repository;

import org.springframework.data.repository.CrudRepository;

import com.skill.model.SkillEntity;

public interface SkillRepository extends CrudRepository<SkillEntity, Integer> {
	
	SkillEntity findByskillId(int skillId);
}
