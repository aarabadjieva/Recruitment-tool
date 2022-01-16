package com.bootcamp.recruitmenttool.data.repositories;

import com.bootcamp.recruitmenttool.data.entities.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitersRepository extends JpaRepository<Recruiter, Long> {
    boolean existsByEmail(String email);

    Recruiter findByEmail(String email);
}
