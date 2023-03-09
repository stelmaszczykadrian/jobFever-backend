package com.jobfever.candidate.service;

import com.jobfever.candidate.model.Candidate;
import com.jobfever.candidate.repository.CandidateRepository;
import com.jobfever.job.repository.JobRepository;
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
}
