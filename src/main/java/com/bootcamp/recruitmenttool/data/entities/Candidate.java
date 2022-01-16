package com.bootcamp.recruitmenttool.data.entities;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "candidates")
public class Candidate extends BaseEntity{

    @Column(nullable = false, name = "first_name")
    @Length(min = 2)
    private String firstName;

    @Column(nullable = false, name = "last_name")
    @Length(min = 2)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String bio;

    @Column(name = "birth_date", nullable = false)
    private String birthDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(
            name = "candidates_skills",
            joinColumns = @JoinColumn(name = "candidate_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private List<Skill> skills;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;

    @OneToMany(mappedBy = "candidate")
    private List<Interview> interviews;

    @ManyToMany(mappedBy = "candidates")
    private List<Job> jobs;

    public Candidate() {
        this.interviews = new ArrayList<>();
    }
}
