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

    public List<Candidate> getCandidateList() {
        return CandidateRepository.candidateList.stream().toList();
    }

//    public boolean checkIfCandidateExists(Candidate candidate){
//        for (Candidate candidate1: CandidateRepository.candidateList) {
//            if (candidate.getName().equals(candidate1.getName())){
//                return true;
//            }
//        }
//        return false;
//    }
    public void addCandidate(String email, String password){
        candidateRepository.save(new Candidate(email,password));
    }

    public Optional<Candidate> getCandidateById(int candidateId){
        return candidateRepository.findById(candidateId);
    }

    public boolean deleteCandidateById(int candidateId) {
        if(getCandidateById(candidateId).isPresent()){
            candidateRepository.deleteById(candidateId);
            return true;
        }
        return false;
    }

    //    change this method to use Hibernate!
    public String editProfileById(int candidateId){
        return "Profile edited by candidate with ID: " + candidateId;
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
