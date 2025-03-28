package org.root.strm;

import java.util.Date;

public class Employee {
    private int id;
    private String name;
    private double salary;
    private int deptId;
    private String gender;
    private Date joinedDate;
    private int age;
    // Constructor
    public Employee(int id, String name, double salary, int deptId, String gender, Date joinedDate, int age) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.deptId = deptId;
        this.gender = gender;
        this.joinedDate = joinedDate;
        this.age = age;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", deptId=" + deptId +
                ", gender='" + gender + '\'' +
                ", joinedDate=" + joinedDate +
                ", age=" + age +
                '}';
    }
}

