/**
 * 
 */
package com.skill.serviceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.skill.model.AssociateSkillsEntity;
import com.skill.model.EmployeeEntity;
import com.skill.model.SkillEntity;
import com.skill.repository.AssociateRepository;
import com.skill.repository.AssociateSkillRepository;
import com.skill.repository.SkillRepository;
import com.skill.service.SkillTrackerService;
import com.skill.vo.EmployeeVO;
import com.skill.vo.SkillsVO;

/**
 * @author Admin
 *
 */
@Service("skillTrackerService")
public class SkillsTrackerServiceImpl implements SkillTrackerService {

	

	@Autowired
	private SkillRepository skillCategoryRepository;
	
	@Autowired
	private AssociateRepository associateRepository;
	
	@Autowired
	private AssociateSkillRepository associateSkillsRepository;

	

	
	@Override
	public List<SkillsVO> viewallskills() {
		List<SkillsVO> skillVOList = new ArrayList<>();
		List<SkillEntity> skillsList = (List<SkillEntity>) skillCategoryRepository.findAll();
		for (SkillEntity skill : skillsList) {
			SkillsVO skillVO = new SkillsVO();
			skillVO.setSkillId(skill.getSkillId());
			skillVO.setSkillName(skill.getSkillName());
			skillVOList.add(skillVO);
		}
		return skillVOList;
	}

	
	public String addAssociates(EmployeeVO employee,MultipartFile file) {
		
		EmployeeEntity empEntity= new EmployeeEntity();
		Optional<EmployeeEntity> associateEntity = associateRepository.findById(employee.getAssociate_id());
		if(associateEntity.isPresent()) {
			empEntity = associateEntity.get();
		}
		try {
		 mapEmployeeDetails(empEntity,employee,file);
		 mapEmployeeSkills(employee);
		}
		catch (Exception e) {
			e.printStackTrace();
			return "could not add associate";
		}
		
		return "successfully added associate";
		
	}
	private void mapEmployeeDetails(EmployeeEntity empEntity,EmployeeVO employee,MultipartFile file) throws IOException {
		empEntity.setEmpName(employee.getName());
		empEntity.setEmail(employee.getEmail());
		empEntity.setEmpId(employee.getAssociate_id());
		empEntity.setGender(employee.getGender());
		empEntity.setLevel(employee.getLevel());
		empEntity.setMobile(employee.getMobile());
		if (file != null && !file.isEmpty()) {
			empEntity.setPic(file.getBytes());
		}
		
		empEntity.setRemark(employee.getRemark());
		empEntity.setStatus(employee.getStatus());
		empEntity.setStrength(employee.getStrength());
		empEntity.setWeakness(employee.getWeakness());
		associateRepository.save(empEntity);
	}
	
	private void mapEmployeeSkills(EmployeeVO employee) {
		
		List<AssociateSkillsEntity> associateSkillEntityList = (List<AssociateSkillsEntity>) associateSkillsRepository
				.findByEmpId(employee.getAssociate_id());
		if (associateSkillEntityList != null && !associateSkillEntityList.isEmpty()) {
			associateSkillsRepository.deleteAll(associateSkillEntityList);
		}
		for(SkillsVO skills:employee.getSkills()) {
			AssociateSkillsEntity assoSkills = new AssociateSkillsEntity();
			assoSkills.setEmpId(employee.getAssociate_id());
			assoSkills.setSkillId(skills.getSkillId());
			assoSkills.setSkillRating(skills.getSkillRating());
			associateSkillsRepository.save(assoSkills);
		}
		
		
		
	}


	@Override
	public List<EmployeeVO> viewallAssociates() {
		List<EmployeeVO> associateVOList = new ArrayList<EmployeeVO>();
		List<EmployeeEntity> empEntityList=(List<EmployeeEntity>) associateRepository.findAll();
		for(EmployeeEntity entity:empEntityList) {
			List<AssociateSkillsEntity> associateSkills = associateSkillsRepository.findByEmpId(entity.getEmpId());
			EmployeeVO employeeVo = mapSkillsToAssociate(entity,associateSkills);
			associateVOList.add(employeeVo);
		}
		return associateVOList;
	}
private EmployeeVO mapSkillsToAssociate(EmployeeEntity entity,List<AssociateSkillsEntity> associateSkills) {
	EmployeeVO employee = new EmployeeVO();
	employee.setAssociate_id(entity.getEmpId());
	employee.setEmail(entity.getEmail());
	employee.setGender(entity.getGender());
	employee.setLevel(entity.getLevel());
	employee.setMobile(entity.getMobile());
	employee.setName(entity.getEmpName());
	employee.setRemark(entity.getRemark());
	employee.setStatus(entity.getStatus());
	employee.setStrength(entity.getStrength());
	employee.setWeakness(entity.getWeakness());
	
	List<SkillsVO> skillList = new ArrayList<>();
	for(AssociateSkillsEntity skillEntity:associateSkills ) {
		SkillsVO skillvo = new SkillsVO();
		SkillEntity skill =(SkillEntity) skillCategoryRepository.findByskillId(skillEntity.getSkillId());
		skillvo.setSkillId(skill.getSkillId());
		skillvo.setSkillName(skill.getSkillName());
		skillvo.setSkillRating(skillEntity.getSkillRating());
		skillList.add(skillvo);
	}
	employee.setSkills(skillList);
	return employee;
}

@Override
public List<EmployeeVO> deleteAssociate(String associateId) {
	associateRepository.deleteById(associateId);
	associateSkillsRepository.deleteByEmpId(associateId);
	return viewallAssociates();
}


@Override
public List<SkillsVO> addSkill(SkillsVO skillVo) {
	SkillEntity entity = new SkillEntity();
	entity.setSkillName(skillVo.getSkillName());
	skillCategoryRepository.save(entity);
	return viewallskills();
}


@Override
public List<SkillsVO> editSkill(SkillsVO skillvo) {
	Optional<SkillEntity> skill = skillCategoryRepository.findById(skillvo.getSkillId());
	if(skill.isPresent()) {
		skill.get().setSkillName(skillvo.getSkillName());
	}
	skillCategoryRepository.save(skill.get());
	return viewallskills();
}


@Override
public List<SkillsVO> deleteSkill(SkillsVO skillvo) {
	
	Optional<SkillEntity> skill = skillCategoryRepository.findById(skillvo.getSkillId());
		skillCategoryRepository.deleteById(skill.get().getSkillId());
		return viewallskills();
}
	

@Override
public byte[] getPic(String associateId) {
	byte[] bFile = null;
	Optional<EmployeeEntity> associate = associateRepository.findById(associateId);
	if (associate.isPresent()) {
		bFile = associate.get().getPic();
	}
	return bFile;
}


}
