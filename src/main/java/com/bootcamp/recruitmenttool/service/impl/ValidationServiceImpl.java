package com.bootcamp.recruitmenttool.service.impl;

import com.bootcamp.recruitmenttool.data.entities.Candidate;
import com.bootcamp.recruitmenttool.data.entities.Recruiter;
import com.bootcamp.recruitmenttool.data.repositories.CandidatesRepository;
import com.bootcamp.recruitmenttool.service.ValidationService;
import com.bootcamp.recruitmenttool.service.models.CandidateCreateServiceModel;
import com.bootcamp.recruitmenttool.service.models.RecruiterCreateServiceModel;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ValidationServiceImpl implements ValidationService {

    private final CandidatesRepository candidatesRepository;
    private static final List<String> ISO_COUNTRIES = Arrays.stream(Locale.getISOCountries())
            .map(c -> {
                Locale locale = new Locale("en", c);
                return locale.getDisplayName().substring(locale.getDisplayName().indexOf('(') + 1, locale.getDisplayName().indexOf(')'));
            })
            .sorted()
            .collect(Collectors.toList());


    public ValidationServiceImpl(CandidatesRepository candidatesRepository) {
        this.candidatesRepository = candidatesRepository;
    }


    private boolean isEmailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches() && !candidatesRepository.existsByEmail(email);
    }

    @Override
    public boolean isValidCandidate(CandidateCreateServiceModel candidate) {
        return isEmailValid(candidate.getEmail()) && isValidRecruiter(candidate.getRecruiter()) && isValidDate(candidate.getBirthDate());
    }

    @Override
    public boolean isValidRecruiter(RecruiterCreateServiceModel recruiter) {
        return isEmailValid(recruiter.getEmail()) && isValidCountry(recruiter.getCountry());
    }

    @Override
    public boolean isValidCountry(String country) {
        return ISO_COUNTRIES.contains(country);
    }

    @Override
    public boolean isValidDate(String date) {
        SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-MM-dd");
        sdfrmt.setLenient(false);
        try {
            sdfrmt.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}
