
	package com.skill.model;

	import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

	@Entity
	@Table(name = "associate_skills")
	public class AssociateSkillsEntity {
		
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name = "id")
		private String id;
		
		@Column(name = "associate_id")
		private String empId;
		
		@Column(name = "skill_id")
		private int skillId;
		
		@Column(name = "skill_rating")
		private String skillRating;

		public String getEmpId() {
			return empId;
		}

		public void setEmpId(String empId) {
			this.empId = empId;
		}

		public int getSkillId() {
			return skillId;
		}

		public void setSkillId(int skillId) {
			this.skillId = skillId;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getSkillRating() {
			return skillRating;
		}

		public void setSkillRating(String skillRating) {
			this.skillRating = skillRating;
		}

		
	}
