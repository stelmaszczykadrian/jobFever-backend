package com.jobfever.service;

import com.jobfever.model.Candidate;
import com.jobfever.repository.CandidateRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class CandidateService {

    public Candidate addCandidate(@RequestBody Candidate candidate){
        if (checkIfCandidateExists(candidate)){
            throw new IllegalArgumentException("User already exists");
        }
        CandidateRepository.candidateList.add(candidate);
        return candidate;
    }

    public List<Candidate> getCandidateList() {
        return CandidateRepository.candidateList.stream().toList();
    }

    public boolean checkIfCandidateExists(Candidate candidate){
        for (Candidate candidate1: CandidateRepository.candidateList) {
            if (candidate.getName().equals(candidate1.getName())){
                return true;
            }
        }
        return false;
    }

    public String getCandidateById(int candidateId){
        return "Candidate with ID: " + candidateId;
    }

    public String editProfileById(int candidateId){
        return "Profile edited by candidate with ID: " + candidateId;
    }

    public boolean deleteCandidateById(int candidateId){
        return false;
    }

    public String getJobOffersAppliedForByCandidateId(int candidateId){
        return "Job offers for which applied candidate: " + candidateId;
    }

    public String getFavouritesJobsByCandidateId(int candidateId){
        return "Favourite job offers of candidate: " + candidateId;
    }


}
