package com.ambergleam.android.governmentsalaries.controller;

import android.content.Context;
import android.util.AttributeSet;

import com.ambergleam.android.governmentsalaries.controller.fastscroller.sectionindicator.title.SectionTitleIndicator;

public class EmployeeSectionTitleIndicator extends SectionTitleIndicator<String> {

    public EmployeeSectionTitleIndicator(Context context) {
        super(context);
    }

    public EmployeeSectionTitleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmployeeSectionTitleIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setSection(String headerChar) {
        setTitleText(headerChar);
    }

}
