package com.jobfever.repository;

import com.jobfever.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    Candidate findCandidateByEmail(String email);

    Candidate findByEmailAndPassword(String email, String password);
}
