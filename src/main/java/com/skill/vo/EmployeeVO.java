package com.skill.vo;

import java.util.ArrayList;
import java.util.List;

public class EmployeeVO {
private String name;
private String associate_id;
private String email;
private String pic;
private Integer mobile;
private String status;
private String level;
private String remark;
private String strength;
private String weakness;
private String gender;
private List<SkillsVO> skills;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getAssociate_id() {
	return associate_id;
}
public void setAssociate_id(String associate_id) {
	this.associate_id = associate_id;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPic() {
	return pic;
}
public void setPic(String pic) {
	this.pic = pic;
}
public Integer getMobile() {
	return mobile;
}
public void setMobile(Integer mobile) {
	this.mobile = mobile;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getStrength() {
	return strength;
}
public void setStrength(String strength) {
	this.strength = strength;
}
public String getWeakness() {
	return weakness;
}
public void setWeakness(String weakness) {
	this.weakness = weakness;
}
public String getGender() {
	return gender;
}
public void setGender(String gender) {
	this.gender = gender;
}
public List<SkillsVO> getSkills() {
	if (skills == null) {
		skills = new ArrayList<SkillsVO>();
	}
	return skills;
}
public void setSkills(List<SkillsVO> skills) {
	this.skills = skills;
}

}
