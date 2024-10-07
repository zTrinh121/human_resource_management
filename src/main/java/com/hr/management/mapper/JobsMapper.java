package com.hr.management.mapper;

import com.hr.management.model.Jobs;
import com.hr.management.model.JobsExample;
import com.hr.management.request.JobsRequest;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JobsMapper {
    long countByExample(JobsExample example);

    int deleteByExample(JobsExample example);

    int deleteByPrimaryKey(Long jobId);

    int insert(JobsRequest row);

    int insertSelective(Jobs row);

    List<Jobs> selectByExample();

    Jobs selectByPrimaryKey(Long jobId);

    int updateByExampleSelective(@Param("row") Jobs row, @Param("example") JobsExample example);

    int updateByExample(@Param("row") Jobs row, @Param("example") JobsExample example);

    int updateByPrimaryKeySelective(Jobs row);

    int updateByPrimaryKey(Jobs row);

    int insertJobTitle(Jobs jobs);

    List<Jobs> selectJobByJobTitle(String jobTitle);
}