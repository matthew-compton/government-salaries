package com.ambergleam.android.governmentsalaries.model;

import java.io.Serializable;

public class Employee implements Comparable, Serializable {

    private String mName;
    private String mOrganization;
    private String mSalary;
    private String mTitle;
    private String mTravelExpenses;
    private String mYear;

    public Employee(FirebaseEmployee employee) {
        this.mName = employee.getName();
        this.mOrganization = employee.getOrganization();
        this.mSalary = employee.getSalary();
        this.mTitle = employee.getTitle();
        this.mTravelExpenses = employee.getTravel();
        this.mYear = employee.getYear();
    }

    public Employee(String name, String organization, String salary, String title, String travelExpenses, String year) {
        this.mName = name;
        this.mOrganization = organization;
        this.mSalary = salary;
        this.mTitle = title;
        this.mTravelExpenses = travelExpenses;
        this.mYear = year;
    }

    public char getFirstChar() {
        char firstChar = Character.toUpperCase(getName().charAt(0));
        if (!Character.isLetter(firstChar)) {
            return '#';
        } else {
            return firstChar;
        }
    }

    public String getName() {
        return mName;
    }

    public String getOrganization() {
        return mOrganization;
    }

    public String getSalary() {
        return mSalary;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getTravelExpenses() {
        return mTravelExpenses;
    }

    public String getYear() {
        return mYear;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(mName).append("\n")
                .append(mOrganization).append("\n")
                .append(mSalary).append("\n")
                .append(mTitle).append("\n")
                .append(mTravelExpenses).append("\n")
                .append(mYear).append("\n")
                .toString();
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + toString().hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Employee) {
            return toString() == o.toString();
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Object another) {
        if (another instanceof Employee) {
            return this.toString().compareTo(another.toString());
        } else {
            return 0;
        }
    }

}
