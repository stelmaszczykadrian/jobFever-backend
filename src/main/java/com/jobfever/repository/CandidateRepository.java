package com.jobfever.repository;

import com.jobfever.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
    Optional<Candidate> findByEmail(String email);

    Candidate findByEmailAndPassword(String email, String password);
}
