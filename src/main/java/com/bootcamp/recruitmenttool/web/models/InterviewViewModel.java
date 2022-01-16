package com.bootcamp.recruitmenttool.web.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InterviewViewModel {

    private CandidateViewModel candidate;
    private RecruiterViewModel recruiter;
    private JobViewModel job;
}
