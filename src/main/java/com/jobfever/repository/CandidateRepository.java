package com.jobfever.repository;

import com.jobfever.model.Candidate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface CandidateRepository {

    public final static List<Candidate> candidateList = new ArrayList<>(Arrays.asList(
            new Candidate(1, "Pawel", "pawel"),
            new Candidate(2, "Robert", "robert"),
            new Candidate(3, "Agnieszka", "agnieszka")
    ));
}
