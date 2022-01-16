package com.bootcamp.recruitmenttool.service;

import com.bootcamp.recruitmenttool.data.entities.Job;
import com.bootcamp.recruitmenttool.service.models.JobCreateServiceModel;
import com.bootcamp.recruitmenttool.service.models.JobServiceModel;

import java.util.List;

public interface JobService {

    void createJob(JobCreateServiceModel model) throws Exception;
    List<JobServiceModel> jobsBySkill(String skillName);
    void deleteJobById (Long id);
    void createInterviews(Job job);
}
