package com.bootcamp.recruitmenttool.service;


import com.bootcamp.recruitmenttool.service.models.CandidateCreateServiceModel;
import com.bootcamp.recruitmenttool.service.models.RecruiterCreateServiceModel;

import java.util.Date;

public interface ValidationService {

    boolean isValidCandidate(CandidateCreateServiceModel candidate);
    boolean isValidRecruiter(RecruiterCreateServiceModel recruiter);
    boolean isValidCountry(String country);
    boolean isValidDate(String date);
}
