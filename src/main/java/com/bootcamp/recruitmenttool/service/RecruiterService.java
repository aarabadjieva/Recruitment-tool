package com.bootcamp.recruitmenttool.service;

import com.bootcamp.recruitmenttool.data.entities.Recruiter;
import com.bootcamp.recruitmenttool.service.models.RecruiterCreateServiceModel;
import com.bootcamp.recruitmenttool.service.models.RecruiterServiceModel;

import java.util.Arrays;
import java.util.List;

public interface RecruiterService {

    Recruiter createRecruiter(RecruiterCreateServiceModel model) throws Exception;

    List<RecruiterServiceModel> findRecruitersWithAvailableCandidates();

    List<RecruiterServiceModel> findRecruitersByLevel(int level);

    void updateRecruiter(Recruiter recruiter);
}
