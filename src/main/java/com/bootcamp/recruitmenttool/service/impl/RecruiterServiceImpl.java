package com.bootcamp.recruitmenttool.service.impl;

import com.bootcamp.recruitmenttool.data.entities.Recruiter;
import com.bootcamp.recruitmenttool.data.entities.Skill;
import com.bootcamp.recruitmenttool.data.repositories.RecruitersRepository;
import com.bootcamp.recruitmenttool.service.RecruiterService;
import com.bootcamp.recruitmenttool.service.ValidationService;
import com.bootcamp.recruitmenttool.service.models.RecruiterCreateServiceModel;
import com.bootcamp.recruitmenttool.service.models.RecruiterServiceModel;
import com.bootcamp.recruitmenttool.service.models.SkillServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecruiterServiceImpl implements RecruiterService {

    private final RecruitersRepository recruitersRepository;
    private final ModelMapper mapper;
    private final ValidationService validator;

    public RecruiterServiceImpl(RecruitersRepository recruitersRepository, ModelMapper mapper, ValidationService validator) {
        this.recruitersRepository = recruitersRepository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public Recruiter createRecruiter(RecruiterCreateServiceModel model) throws Exception {
        if (recruitersRepository.existsByEmail(model.getEmail())) {
            return recruitersRepository.findByEmail(model.getEmail());
        } else {
            Recruiter recruiter = mapper.map(model, Recruiter.class);
            recruitersRepository.saveAndFlush(recruiter);
            return recruiter;
        }
    }

    @Override
    public List<RecruiterServiceModel> findRecruitersWithAvailableCandidates() {

        return recruitersRepository.findAll()
                .stream()
                .filter((Recruiter r) -> r.getCandidates().size() > 0)
                .map(r -> mapper.map(r, RecruiterServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RecruiterServiceModel> findRecruitersByLevel(int level) {
        return recruitersRepository.findAll()
                .stream()
                .filter((Recruiter r) -> r.getLevel() == level)
                .map(r -> mapper.map(r, RecruiterServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void updateRecruiter(Recruiter recruiter) {
     // Recruiter recruiter = recruitersRepository.findById(id).orElse(null);
     // assert recruiter != null;
     // recruiter.setAvailableSlots(recruiter.getAvailableSlots() + 1);
        recruitersRepository.saveAndFlush(recruiter);
    }
}
