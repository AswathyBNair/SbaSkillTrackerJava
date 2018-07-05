
	package com.skill.model;

	import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

	@Entity
	@Table(name = "associate_table")
	public class EmployeeEntity {
		
		@Id
		@Column(name = "associate_id")
		private String empId;
		
		@Column(name = "name")
		private String empName;
		
		@Column(name = "email")
		private String email;
		
		@Column(name = "mobile")
		private int mobile;
		
		@Lob
		@Column(name = "pic")
		private byte[] pic;
		
		@Column(name = "status")
		private String status;
		
		@Column(name = "level")
		private String level;
		
		@Column(name = "remark")
		private String remark;
		
		@Column(name = "strength")
		private String strength;
		
		@Column(name = "weakness")
		private String weakness;
		
		@Column(name = "gender")
		private String gender;

		

		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empId) {
			this.empId = empId;
		}

		public String getEmpName() {
			return empName;
		}

		public void setEmpName(String empName) {
			this.empName = empName;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		
		public int getMobile() {
			return mobile;
		}

		public void setMobile(int mobile) {
			this.mobile = mobile;
		}

		

		public byte[] getPic() {
			return pic;
		}

		public void setPic(byte[] pic) {
			this.pic = pic;
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
		
		
	}
