package com.bootcamp.recruitmenttool.service;

import com.bootcamp.recruitmenttool.data.entities.Candidate;
import com.bootcamp.recruitmenttool.data.entities.Interview;
import com.bootcamp.recruitmenttool.data.entities.Job;
import com.bootcamp.recruitmenttool.data.entities.Recruiter;
import com.bootcamp.recruitmenttool.service.models.InterviewServiceModel;

import java.util.List;

public interface InterviewService {

    void createInterview(Candidate candidate, Recruiter recruiter, Job job);
    List<InterviewServiceModel> allInterviews();
}
