package com.ambergleam.android.governmentsalaries;

public class Employee {

    private String name;
    private String organization;
    private String salary;
    private String title;
    private String travel;
    private String year;

    public Employee() {
        // empty default constructor, necessary for Firebase to be able to deserialize employees
    }

    public String getName() {
        return name;
    }

    public String getOrganization() {
        return organization;
    }

    public String getSalary() {
        return salary;
    }

    public String getTitle() {
        return title;
    }

    public String getTravel() {
        return travel;
    }

    public String getYear() {
        return year;
    }

}
