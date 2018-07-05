/**
 * 
 */
package com.skill.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skill.service.SkillTrackerService;
import com.skill.vo.EmployeeVO;
import com.skill.vo.SkillsVO;

/**
 * @author Admin
 *
 */
@RestController
@CrossOrigin(origins = "*") 
/**Use the below mapping when you are running this spring boot application using jar.*/

public class SkillTrackerController {
	
	@Autowired
	private SkillTrackerService skillTrackerService;
	
	@CrossOrigin(origins = "*") 
	@RequestMapping(value="/viewallskills", method = RequestMethod.GET)
	public List<SkillsVO> viewallskills() {
		return skillTrackerService.viewallskills();
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/addAssociates")
	public ResponseEntity<String> addAssociate(@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam("data") String associateData) {
		try {
			EmployeeVO empVO = new ObjectMapper().readValue(associateData, EmployeeVO.class);
			String status = skillTrackerService.addAssociates(empVO, file);
			return ResponseEntity.status(HttpStatus.OK).body(status);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("failure");
		}
	}
	
	@CrossOrigin(origins = "*") 
	@RequestMapping(value="/viewallAssociates", method = RequestMethod.GET)
	public List<EmployeeVO> viewallAssociates() {
		return skillTrackerService.viewallAssociates();
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/deleteEmployee/{associateId}", method = RequestMethod.DELETE)
	public List<EmployeeVO> deleteAssociate(@PathVariable String associateId) {
		return skillTrackerService.deleteAssociate(associateId);
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/addskill", method = RequestMethod.POST)
	public List<SkillsVO> addSkill(@RequestBody SkillsVO skillVO) {
		return skillTrackerService.addSkill(skillVO);
	}
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/editskill", method = RequestMethod.POST)
	public List<SkillsVO> editSkill(@RequestBody SkillsVO skillVO) {
		return skillTrackerService.editSkill(skillVO);
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/deleteskill", method = RequestMethod.DELETE)
	public List<SkillsVO> deleteSkill(@RequestBody SkillsVO skillVO) {
		return skillTrackerService.deleteSkill(skillVO);
	}
	@GetMapping(value = "/getimage/{associateId}", produces = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	public byte[] getFile(@PathVariable String associateId) {
		byte[] bFile = null;
		try {
			bFile = skillTrackerService.getPic(associateId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bFile;
	}
	
}
