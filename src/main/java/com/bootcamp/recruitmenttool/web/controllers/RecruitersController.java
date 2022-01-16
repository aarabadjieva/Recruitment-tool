package com.bootcamp.recruitmenttool.web.controllers;

import com.bootcamp.recruitmenttool.data.entities.Recruiter;
import com.bootcamp.recruitmenttool.service.RecruiterService;
import com.bootcamp.recruitmenttool.web.models.RecruiterViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/recruiters")
public class RecruitersController {

    private final RecruiterService recruiterService;
    private final ModelMapper mapper;

    public RecruitersController(RecruiterService recruiterService, ModelMapper mapper) {
        this.recruiterService = recruiterService;
        this.mapper = mapper;
    }

    @GetMapping
    public List<RecruiterViewModel> recruiters() {
        return recruiterService.findRecruitersWithAvailableCandidates()
                .stream()
                .map(r -> mapper.map(r, RecruiterViewModel.class))
                .collect(Collectors.toList());
    }

    @GetMapping
    @RequestMapping(params = "level")
    public List<RecruiterViewModel> recruitersByLevel (@RequestParam int level){
        return recruiterService.findRecruitersByLevel(level)
                .stream()
                .map(r -> mapper.map(r, RecruiterViewModel.class))
                .collect(Collectors.toList());
    }
}
