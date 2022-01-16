package com.bootcamp.recruitmenttool.data.repositories;

import com.bootcamp.recruitmenttool.data.entities.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CandidatesRepository extends JpaRepository<Candidate, Long> {
    boolean existsByEmail(String email);

    Candidate findByEmail(String email);
}
