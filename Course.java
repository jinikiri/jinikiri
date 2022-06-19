package com.dailycodebuffer.spring.data.jpa.tutorial.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
@Entity
public class Course {
	@Id
	@SequenceGenerator(
			name="course_sequence",
			sequenceName="course_sequence",
			allocationSize=1
			)
	@GeneratedValue(
			strategy=GenerationType.SEQUENCE,
			generator="course_sequence")
	private long courseid;
	private String title;
	private int credit;
	public long getCourseid() {
		return courseid;
	}
	public void setCourseid(long courseid) {
		this.courseid = courseid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	
	
	@OneToOne(mappedBy = "course")
	private CourseMaterial coursematerial;
	
	public CourseMaterial getCoursematerial() {
		return coursematerial;
	}
	public void setCoursematerial(CourseMaterial coursematerial) {
		this.coursematerial = coursematerial;
	}
	

	@ManyToOne(
			cascade = CascadeType.ALL
			)
	@JoinColumn(
			name = "teacher_id",
			referencedColumnName = "teacherid")
	private Teacher teacher;
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	 @ManyToMany(cascade = CascadeType.ALL)
	 @JoinTable(
			     name="student_course_map",
				 joinColumns = @JoinColumn
				 (
						 name="course_id",
						 referencedColumnName = "courseid"
				 ),
				 inverseJoinColumns = @JoinColumn
				 (
						 name="student_id",
						 referencedColumnName = "studentid"
				 )
			 )
	 
	 private List<Student> students;
	 
	 
	 public List<Student> getStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}
	public void addStudents(Student student)
	 {
		 if(students==null)
		 {
			 students=new ArrayList<>();
			 students.add(student);
		 }
	 }

}
