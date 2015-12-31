package com.ambergleam.android.governmentsalaries.model;

public class MockEmployee extends Employee{

    public static final String MOCK_NAME = "Burdell, George P";
    public static final String MOCK_ORGANIZATION = "Georgia Tech";
    public static final String MOCK_SALARY = "$120,000.00";
    public static final String MOCK_TITLE = "Scientist";
    public static final String MOCK_TRAVEL = "$10,000.00";
    public static final String MOCK_YEAR = "2015";

    public MockEmployee() {
        super(MOCK_NAME, MOCK_ORGANIZATION, MOCK_SALARY, MOCK_TITLE, MOCK_TRAVEL, MOCK_YEAR);
    }

}
