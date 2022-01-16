package com.bootcamp.recruitmenttool.service.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterviewServiceModel {

    private CandidateServiceModel candidate;
    private RecruiterServiceModel recruiter;
    private JobServiceModel job;
}
