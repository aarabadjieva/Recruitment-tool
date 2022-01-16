package com.bootcamp.recruitmenttool.service.impl;

import com.bootcamp.recruitmenttool.data.entities.Candidate;
import com.bootcamp.recruitmenttool.data.entities.Recruiter;
import com.bootcamp.recruitmenttool.data.entities.Skill;
import com.bootcamp.recruitmenttool.data.repositories.CandidatesRepository;
import com.bootcamp.recruitmenttool.service.CandidatesService;
import com.bootcamp.recruitmenttool.service.RecruiterService;
import com.bootcamp.recruitmenttool.service.SkillsService;
import com.bootcamp.recruitmenttool.service.ValidationService;
import com.bootcamp.recruitmenttool.service.models.CandidateCreateServiceModel;
import com.bootcamp.recruitmenttool.service.models.CandidateServiceModel;
import com.bootcamp.recruitmenttool.service.models.SkillServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CandidatesServiceImpl implements CandidatesService {

    private final CandidatesRepository candidatesRepository;
    private final ModelMapper mapper;
    private final ValidationService validator;
    private final RecruiterService recruiterService;
    private final SkillsService skillsService;

    public CandidatesServiceImpl(CandidatesRepository candidatesRepository, ModelMapper mapper, ValidationService validator, RecruiterService recruiterService, SkillsService skillsService) {
        this.candidatesRepository = candidatesRepository;
        this.mapper = mapper;
        this.validator = validator;
        this.recruiterService = recruiterService;
        this.skillsService = skillsService;
    }

    @Override
    public List<CandidateServiceModel> allCandidates() {
        return candidatesRepository.findAll().stream()
                .map(c -> mapper.map(c, CandidateServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public CandidateServiceModel createCandidate(CandidateCreateServiceModel model) throws Exception {
        if (validator.isValidCandidate(model)) {
            Recruiter recruiter = recruiterService.createRecruiter(model.getRecruiter());
            Candidate candidate = mapper.map(model, Candidate.class);
            candidate.setRecruiter(recruiter);
            updateSkills(candidate, model);
            candidatesRepository.saveAndFlush(candidate);
            return mapper.map(model, CandidateServiceModel.class);
        } else {
            throw new Exception("Invalid candidate");

        }
    }

    @Override
    public void updateCandidate(CandidateCreateServiceModel model, Long id) {
        Candidate candidate = candidatesRepository.findById(id).orElse(null);
        BeanUtils.copyProperties(model, candidate, "id");
        updateSkills(candidate, model);
        candidatesRepository.saveAndFlush(candidate);
    }

    @Override
    public void deleteCandidate(Long id) {
        candidatesRepository.deleteById(id);
    }

    @Override
    public CandidateServiceModel getById(Long id) {
        return mapper.map(candidatesRepository.getById(id), CandidateServiceModel.class);
    }


    private void updateSkills(Candidate candidate, CandidateCreateServiceModel model) {
        List<Skill> skills = new ArrayList<>();
        for (SkillServiceModel skill : model.getSkills()
        ) {
            skills.add(skillsService.createAndReturnSkill(skill));
        }
        candidate.setSkills(skills);
    }
}