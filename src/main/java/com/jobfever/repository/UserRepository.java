package com.jobfever.repository;

import com.jobfever.model.Candidate;
import com.jobfever.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    @Query("SELECT max(ch.id) FROM Candidate ch")
    Integer findCandidateLastId();
    @Query("SELECT max(ch.id) FROM Employer ch")
    Integer findEmployerLastId();
}
