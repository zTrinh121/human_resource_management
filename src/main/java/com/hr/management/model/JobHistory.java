package com.hr.management.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.Date;

public class JobHistory extends JobHistoryKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_HISTORY.END_DATE
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */

    @NotNull(message = "End date is required")
    private LocalDate endDate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_HISTORY.JOB_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    @NotNull(message = "Job ID is required")
    private Long jobId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column JOB_HISTORY.DEPARTMENT_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    @NotNull(message = "Department ID is required")
    private Long departmentId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_HISTORY.END_DATE
     *
     * @return the value of JOB_HISTORY.END_DATE
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_HISTORY.END_DATE
     *
     * @param endDate the value for JOB_HISTORY.END_DATE
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_HISTORY.JOB_ID
     *
     * @return the value of JOB_HISTORY.JOB_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public Long getJobId() {
        return jobId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_HISTORY.JOB_ID
     *
     * @param jobId the value for JOB_HISTORY.JOB_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column JOB_HISTORY.DEPARTMENT_ID
     *
     * @return the value of JOB_HISTORY.DEPARTMENT_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public Long getDepartmentId() {
        return departmentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column JOB_HISTORY.DEPARTMENT_ID
     *
     * @param departmentId the value for JOB_HISTORY.DEPARTMENT_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}