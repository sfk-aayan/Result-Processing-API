package com.example.project;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "studentId")
    private int studentId;

    @Column(name = "name")
    private String name;

    @Column(name = "semester")
    private String semester;

    @Column(name = "cgpa")
    private double cgpa;

    public Student(){}
    public Student(int studentId, String name, String semester, double cgpa) {
        this.studentId = studentId;
        this.name = name;
        this.semester = semester;
        this.cgpa = cgpa;
    }

    public void setStudentId(int studentId){
        this.studentId = studentId;
    }

    public int getStudentId(){
        return this.studentId;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSemester(String semester){
        this.semester = semester;
    }

    public String getSemester(){
        return this.semester;
    }

    public void setCgpa(double cgpa){
        this.cgpa = cgpa;
    }

    public double getCgpa(){
        return this.cgpa;
    }

    @Override
    public String toString() {
        return "Student [studentId=" + studentId + ", name=" + name + ", semester=" + semester + ", cgpa=" + cgpa + "]";
    }

}

