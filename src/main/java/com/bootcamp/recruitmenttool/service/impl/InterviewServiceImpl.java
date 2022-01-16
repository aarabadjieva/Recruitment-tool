package com.bootcamp.recruitmenttool.service.impl;

import com.bootcamp.recruitmenttool.data.entities.Candidate;
import com.bootcamp.recruitmenttool.data.entities.Interview;
import com.bootcamp.recruitmenttool.data.entities.Job;
import com.bootcamp.recruitmenttool.data.entities.Recruiter;
import com.bootcamp.recruitmenttool.data.repositories.InterviewsRepository;
import com.bootcamp.recruitmenttool.service.InterviewService;
import com.bootcamp.recruitmenttool.service.models.InterviewServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterviewServiceImpl implements InterviewService {

    private final InterviewsRepository interviewsRepository;
    private final ModelMapper mapper;

    public InterviewServiceImpl(InterviewsRepository interviewsRepository, ModelMapper mapper) {
        this.interviewsRepository = interviewsRepository;
        this.mapper = mapper;
    }

    @Override
    public void createInterview(Candidate candidate, Recruiter recruiter, Job job) {
        Interview interview = new Interview(candidate, recruiter, job);
        interviewsRepository.saveAndFlush(interview);
    }

    @Override
    public List<InterviewServiceModel> allInterviews() {

        return interviewsRepository.findAll()
                .stream()
                .map(i -> mapper.map(i, InterviewServiceModel.class))
                .collect(Collectors.toList());
    }
}
