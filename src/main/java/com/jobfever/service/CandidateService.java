package com.jobfever.service;

import com.jobfever.model.*;
import com.jobfever.repository.CandidateRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CandidateService {


    private EntityManager entityManager;
    private CandidateRepository candidateRepository;

    @Autowired
    public CandidateService(CandidateRepository candidateRepository, EntityManager entityManager) {
        this.candidateRepository = candidateRepository;
        this.entityManager = entityManager;
    }

    public Optional<Candidate> getCandidateById(int candidateId) {
        return candidateRepository.findById(candidateId);
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
            c.setEmail(candidate.getEmail());

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

    @Transactional
    public int addCandidateEducation(int candidateId, CandidateEducation candidateEducation) {
        Candidate tempCandidate = new Candidate();
        tempCandidate.setId(candidateId);
        candidateEducation.setCandidate(tempCandidate);

        entityManager.persist(candidateEducation);
        return candidateEducation.getId();
    }

    public void editCandidateExperience(int candidateId, int experienceId, CandidateExperience candidateExperience) {
        Optional<Candidate> candidateToUpdate = getCandidateById(candidateId);
        candidateToUpdate.ifPresent(c -> {
            for (CandidateExperience experience : c.getCandidateExperiences()) {
                CandidateExperience existingExperience = c.getCandidateExperiences().stream()
                        .filter(e -> e.getId() == experienceId)
                        .findFirst()
                        .orElseThrow(() -> new IllegalArgumentException("Cannot find experience with id: " + experienceId));
                existingExperience.setPosition(candidateExperience.getPosition());
                existingExperience.setCompanyName(candidateExperience.getCompanyName());
                existingExperience.setLocation(candidateExperience.getLocation());
                existingExperience.setStartDate(candidateExperience.getStartDate());
                existingExperience.setEndDate(candidateExperience.getEndDate());
                existingExperience.setIndustry(candidateExperience.getIndustry());
                existingExperience.setDescription(candidateExperience.getDescription());
            }
            candidateRepository.save(c);
        });
    }

    @Transactional
    public int addCandidateExperience(int candidateId, CandidateExperience candidateExperience) {
        Candidate tempCandidate = new Candidate();
        tempCandidate.setId(candidateId);
        candidateExperience.setCandidate(tempCandidate);

        entityManager.persist(candidateExperience);
        return candidateExperience.getId();
    }

    public boolean deleteCandidateExperienceById(int candidateId, int experienceId) {
        Optional<Candidate> candidateToUpdate = getCandidateById(candidateId);
        if (candidateToUpdate.isPresent()) {
            Candidate candidate = candidateToUpdate.get();
            List<CandidateExperience> experiences = candidate.getCandidateExperiences();
            CandidateExperience experienceToDelete = experiences.stream()
                    .filter(e -> e.getId() == experienceId)
                    .findFirst()
                    .orElse(null);

            if (experienceToDelete != null) {
                experiences.remove(experienceToDelete);
                candidateRepository.save(candidate);
                return true;
            }
        }
        return false;
    }

    public boolean deleteCandidateEducationById(int candidateId, int educationId) {
        Optional<Candidate> candidateToUpdate = getCandidateById(candidateId);
        if (candidateToUpdate.isPresent()) {
            Candidate candidate = candidateToUpdate.get();
            List<CandidateEducation> educations = candidate.getCandidateEducations();
            CandidateEducation educationToDelete = educations.stream()
                    .filter(e -> e.getId() == educationId)
                    .findFirst()
                    .orElse(null);

            if (educationToDelete != null) {
                educations.remove(educationToDelete);
                candidateRepository.save(candidate);
                return true;
            }
        }
        return false;
    }

    public void addFilename(int id, String filename) {
        Optional<Candidate> candidateToUpdate = getCandidateById(id);
        candidateToUpdate.ifPresent(e -> {
            e.setImgFileName(filename);
        });
        candidateRepository.save(candidateToUpdate.orElseThrow(() -> new IllegalArgumentException("Cannot find candidate with id: " + id)));
    }

    public void addCvFile(int id, String filename) {
        Optional<Candidate> candidateToUpdate = getCandidateById(id);
        candidateToUpdate.ifPresent(e -> {
            e.setCvFile(filename);
        });
        candidateRepository.save(candidateToUpdate.orElseThrow(() -> new IllegalArgumentException("Cannot find candidate with id: " + id)));
    }

    public Set<Candidate> findCandidatesByIds(Set<Integer> candidateIds) {
        Set<Candidate> candidates = new HashSet<>();
        for (Integer id : candidateIds) {
            Optional<Candidate> optionalCandidate = candidateRepository.findById(id);
            if (optionalCandidate.isPresent()) {
                candidates.add(optionalCandidate.get());
            }
        }
        return candidates;
    }

    public void addRating(int id, int rating, int employerId, int jobId) {

        Optional<Candidate> candidateToUpdate = getCandidateById(id);
        candidateToUpdate.ifPresent(employer -> {
            List<Rating> ratingListToUpdate = employer.getRating();
                ratingListToUpdate.add(Rating.builder()
                        .ratingValue(rating)
                                .employerId(employerId)
                                .jobId(jobId)
                        .build());
                employer.setRating(ratingListToUpdate);
        });
        candidateRepository.save(candidateToUpdate.orElseThrow(() -> new IllegalArgumentException("Cannot find employer with id:" + id)));
    }

    public int getRating(int id, int employerId, int jobId) {
        Optional<Candidate> optionalCandidate = getCandidateById(id);
        Candidate candidate = new Candidate();
        if (optionalCandidate.isPresent()) {
            candidate = optionalCandidate.get();
        }
        List<Rating> ratingList = candidate.getRating();
        if (ratingList.size() > 0) {
            Optional<Integer> sumOfRatings = ratingList.stream()
                    .filter(rating -> rating.getEmployerId() == employerId && rating.getJobId() == jobId)
                    .map(Rating::getRatingValue)
                    .findFirst();
            if (sumOfRatings.isPresent()){
                return sumOfRatings.get();
            }
        }
            return 0;
        }
    }

