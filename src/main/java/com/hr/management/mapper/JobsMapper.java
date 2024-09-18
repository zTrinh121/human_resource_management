package com.hr.management.mapper;

import com.hr.management.model.Jobs;
import com.hr.management.model.JobsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JOBS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    long countByExample(JobsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JOBS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int deleteByExample(JobsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JOBS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int deleteByPrimaryKey(Long jobId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JOBS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int insert(Jobs row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JOBS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int insertSelective(Jobs row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JOBS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    List<Jobs> selectByExample(JobsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JOBS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    Jobs selectByPrimaryKey(Long jobId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JOBS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByExampleSelective(@Param("row") Jobs row, @Param("example") JobsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JOBS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByExample(@Param("row") Jobs row, @Param("example") JobsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JOBS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByPrimaryKeySelective(Jobs row);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table JOBS
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    int updateByPrimaryKey(Jobs row);
}