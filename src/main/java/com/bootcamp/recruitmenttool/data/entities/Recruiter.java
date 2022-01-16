package com.bootcamp.recruitmenttool.data.entities;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "recruiters")
public class Recruiter extends BaseEntity{

    @Column(nullable = false, name = "last_name")
    @Length(min = 2)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String country;
    @Column
    @ColumnDefault(value = "5")
    @Max(5)
    private int availableSlots;
    @Column(columnDefinition = "int default 1")
    private int level;

    @OneToMany(mappedBy = "recruiter")
    List<Interview> interviews;

    @OneToMany(mappedBy = "recruiter", fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    List<Candidate> candidates;
}

