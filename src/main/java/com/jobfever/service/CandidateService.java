package com.jobfever.service;

import com.jobfever.model.Candidate;
import com.jobfever.model.Employer;
import com.jobfever.repository.CandidateRepository;
import com.jobfever.repository.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {
    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Optional<Candidate> getCandidateById(int candidateId){
        return candidateRepository.findById(candidateId);
    }

    public void addCandidate(Candidate candidate){
        candidateRepository.save(candidate);
    }

    public boolean deleteCandidateById(int candidateId) {
        if(getCandidateById(candidateId).isPresent()){
            candidateRepository.deleteById(candidateId);
            return true;
        }
        return false;
    }

    public void editProfileById(int candidateId, Candidate candidate){
        Optional<Candidate> candidateToUpdate = getCandidateById(candidateId);

        candidateToUpdate.ifPresent(c -> {
            c.setEmail(candidate.getEmail());
            c.setPassword(candidate.getPassword());
        });

        candidateRepository.save(candidateToUpdate.orElseThrow(() -> new IllegalArgumentException("Cannot find candidate with id: " + candidateId)));
    }

    //    change this method to use Hibernate!
    public String getJobOffersAppliedForByCandidateId(int candidateId){
        return "Job offers for which applied candidate: " + candidateId;
    }

    //    change this method to use Hibernate!
    public String getFavouritesJobsByCandidateId(int candidateId){
        return "Favourite job offers of candidate: " + candidateId;
    }
}
