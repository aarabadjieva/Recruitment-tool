package com.bootcamp.recruitmenttool.service.impl;

import com.bootcamp.recruitmenttool.data.entities.*;
import com.bootcamp.recruitmenttool.data.repositories.JobsRepository;
import com.bootcamp.recruitmenttool.service.InterviewService;
import com.bootcamp.recruitmenttool.service.JobService;
import com.bootcamp.recruitmenttool.service.RecruiterService;
import com.bootcamp.recruitmenttool.service.SkillsService;
import com.bootcamp.recruitmenttool.service.models.JobCreateServiceModel;
import com.bootcamp.recruitmenttool.service.models.JobServiceModel;
import com.bootcamp.recruitmenttool.service.models.SkillServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements JobService {

    private final JobsRepository jobsRepository;
    private final ModelMapper mapper;
    private final SkillsService skillsService;
    private final InterviewService interviewService;
    private final RecruiterService recruiterService;

    public JobServiceImpl(JobsRepository jobsRepository, ModelMapper mapper, SkillsService skillsService, InterviewService interviewService, RecruiterService recruiterService) {
        this.jobsRepository = jobsRepository;
        this.mapper = mapper;
        this.skillsService = skillsService;
        this.interviewService = interviewService;
        this.recruiterService = recruiterService;
    }

    @Override
    public void createJob(JobCreateServiceModel model) throws Exception {
        Job job = mapper.map(model, Job.class);
        job.getSkills().clear();
        for (SkillServiceModel s:model.getSkills()
             ) {
            skillsService.findSkillByName(s.getName())
                    .ifPresent(skill -> job.getSkills().add(skill));
        }
        jobsRepository.saveAndFlush(job);
        createInterviews(job);
    }

    @Override
    public List<JobServiceModel> jobsBySkill(String skillName) {
        Skill skill = skillsService.findSkillByName(skillName).orElse(null);
        return jobsRepository.findAllBySkills(skill)
                .stream()
                .map(j -> mapper.map(j, JobServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteJobById(Long id) {
        Job job = jobsRepository.getById(id);
        List<Interview> interviews = job.getInterviews();
        for (Interview i: interviews
             ) {
            i.getRecruiter().setAvailableSlots(i.getRecruiter().getAvailableSlots() + 1);
            recruiterService.updateRecruiter(i.getRecruiter());
        }
        jobsRepository.deleteById(id);
    }

    @Override
    public void createInterviews(Job job) {
        for (Skill s:job.getSkills()
             ) {
            for (Candidate c:s.getCandidates()
                 ) {
                if (job.getCandidates().contains(c)) {
                    return;
                } else {
                    Recruiter recruiter = c.getRecruiter();
                    if (recruiter.getAvailableSlots() > 0) {
                        interviewService.createInterview(c, recruiter, job);
                        job.getCandidates().add(c);
                        recruiter.setAvailableSlots(recruiter.getAvailableSlots() - 1);
                        recruiter.setLevel(recruiter.getLevel() + 1);
                        recruiterService.updateRecruiter(recruiter);
                    }
                }
            }
        }
    }

}
