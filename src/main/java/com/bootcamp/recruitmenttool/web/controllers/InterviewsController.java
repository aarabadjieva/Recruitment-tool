package com.bootcamp.recruitmenttool.web.controllers;

import com.bootcamp.recruitmenttool.service.InterviewService;
import com.bootcamp.recruitmenttool.web.models.InterviewViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/interviews")
public class InterviewsController {

    private final InterviewService interviewService;
    private final ModelMapper mapper;

    public InterviewsController(InterviewService interviewService, ModelMapper mapper) {
        this.interviewService = interviewService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<InterviewViewModel> allInterviews(){
        return interviewService.allInterviews()
                .stream()
                .map(i->mapper.map(i, InterviewViewModel.class))
                .collect(Collectors.toList());
    }
}
