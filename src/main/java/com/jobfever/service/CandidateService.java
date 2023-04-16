package com.jobfever.service;

import com.jobfever.model.Candidate;
import com.jobfever.model.CandidateEducation;
import com.jobfever.repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CandidateService {
    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public Optional<Candidate> getCandidateById(int candidateId) {
        return candidateRepository.findById(candidateId);
    }

    public void addCandidate(Candidate candidate) {
        candidateRepository.save(candidate);
    }

    public boolean deleteCandidateById(int candidateId) {
        if (getCandidateById(candidateId).isPresent()) {
            candidateRepository.deleteById(candidateId);
            return true;
        }
        return false;
    }

    public void editProfileById(int candidateId, Candidate candidate) {
        Optional<Candidate> candidateToUpdate = getCandidateById(candidateId);

        candidateToUpdate.ifPresent(c -> {
            c.setName(candidate.getName());
            c.setCity(candidate.getCity());
            c.setLinkedin(candidate.getLinkedin());
            c.setGithub(candidate.getGithub());

            candidateRepository.save(c);
        });
    }

    //    change this method to use Hibernate!
    public String getJobOffersAppliedForByCandidateId(int candidateId) {
        return "Job offers for which applied candidate: " + candidateId;
    }

    //    change this method to use Hibernate!
    public String getFavouritesJobsByCandidateId(int candidateId) {
        return "Favourite job offers of candidate: " + candidateId;
    }

    public boolean isCandidateExists(String email) {
        Candidate candidate = candidateRepository.findCandidateByEmail(email);
        return candidate != null;
    }

    public Candidate login(String email, String password) {
        Candidate existingCandidate = candidateRepository.findByEmailAndPassword(email, password);

        if (existingCandidate != null) {
            return existingCandidate;
        } else {
            return null;
        }

    }

    public void editCandidateEducation(int candidateId, int educationId, CandidateEducation candidateEducation) {
        Optional<Candidate> candidateToUpdate = getCandidateById(candidateId);
        candidateToUpdate.ifPresent(c -> {
            for (CandidateEducation education : c.getCandidateEducations()) {
                CandidateEducation existingEducation = c.getCandidateEducations().stream()
                        .filter(e -> e.getId() == educationId)
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Cannot find education with id: " + educationId));
                existingEducation.setSchool(candidateEducation.getSchool());
                existingEducation.setDegree(candidateEducation.getDegree());
                existingEducation.setFieldOfStudy(candidateEducation.getFieldOfStudy());
                existingEducation.setStartDate(candidateEducation.getStartDate());
                existingEducation.setEndDate(candidateEducation.getEndDate());
                existingEducation.setDescription(candidateEducation.getDescription());
            }
            candidateRepository.save(c);
        });
    }

    public void addCandidateEducation(int candidateId, CandidateEducation candidateEducation) {
        Optional<Candidate> candidateToUpdate = getCandidateById(candidateId);

        Candidate tempCandidate = new Candidate();
        tempCandidate.setId(candidateId);
        candidateEducation.setCandidate(tempCandidate);

        candidateToUpdate.ifPresent(c -> {
            List<CandidateEducation> candidateEducations = c.getCandidateEducations();
            candidateEducations.add(candidateEducation);
            c.setCandidateEducations(candidateEducations);

            candidateRepository.save(c);
        });
    }
}
