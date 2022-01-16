package com.bootcamp.recruitmenttool.web.controllers;

import com.bootcamp.recruitmenttool.service.JobService;
import com.bootcamp.recruitmenttool.service.models.JobCreateServiceModel;
import com.bootcamp.recruitmenttool.web.models.JobViewModel;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jobs")
public class JobsController {

    private final JobService jobService;
    private final ModelMapper mapper;

    public JobsController(JobService jobService, ModelMapper mapper) {
        this.jobService = jobService;
        this.mapper = mapper;
    }

    @PostMapping
    public void createJob(@RequestBody final JobCreateServiceModel model) throws Exception {
        jobService.createJob(model);
    }

    @GetMapping
    @RequestMapping(params = "skill")
    public List<JobViewModel> jobsBySkill(@RequestParam String skill) {
        return jobService.jobsBySkill(skill)
                .stream()
                .map(j -> mapper.map(j, JobViewModel.class))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteJobById(@PathVariable Long id){
        jobService.deleteJobById(id);
    }
}
