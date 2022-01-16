package com.bootcamp.recruitmenttool.data.repositories;

import com.bootcamp.recruitmenttool.data.entities.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewsRepository extends JpaRepository<Interview, Long> {
}
