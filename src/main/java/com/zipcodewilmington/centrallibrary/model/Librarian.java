package com.zipcodewilmington.centrallibrary.model;

public class Librarian extends Person {

private String employeeId;
private String department;
private double salary;

public Librarian(
        String name,
        int age,
        String email,
        String phoneNumber,
        String employeeId,
        String department,
        double salary) {

    super(name, age, email, phoneNumber);

    this.employeeId = employeeId;
    this.department = department;
    this.salary = salary;
        }
    public String getEmployedId() {
        return employeeId;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
}

public void setDepartment(String department) {
    this.department = department;
}

public void setSalary(double salary) {
    this.salary = salary;

        
    }

}

