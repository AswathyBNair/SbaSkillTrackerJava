package com.skill.service;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.skill.vo.EmployeeVO;
import com.skill.vo.SkillsVO;

public interface SkillTrackerService {

	

	List<SkillsVO> viewallskills();
	String addAssociates(EmployeeVO employee,MultipartFile file);
	List<EmployeeVO> viewallAssociates();
	
	List<EmployeeVO> deleteAssociate(String assId);
	List<SkillsVO> addSkill(SkillsVO skillVo);
	
	List<SkillsVO> editSkill(SkillsVO skillvo);
	
	List<SkillsVO> deleteSkill(SkillsVO skillvo);
	byte[] getPic(String associateId);

	

}
