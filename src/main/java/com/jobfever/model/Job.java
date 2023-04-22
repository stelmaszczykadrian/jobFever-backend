package com.jobfever.model;

import com.jobfever.model.enums.CurrencyType;
import com.jobfever.model.enums.JobType;
import com.jobfever.model.enums.WorkType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;
    private LocalDateTime postingDate;

    @ManyToOne
    private Employer employer;

    @NotEmpty
    private String title;
    @NotEmpty
    @Size(max = 2000)
    private String description;

    @ElementCollection
    @Size(min = 1)
    private List<String> technicalRequirements;

    @ElementCollection
    private List<Integer> candidateIds;

    @NotEmpty
    @Size(max = 2000)
    private String responsibilities;

    @NotEmpty
    @Size(max = 2000)
    private String whoWeAreLookingFor;

    @NotEmpty
    @Size(max = 2000)
    private String benefits;

    @NotEmpty
    private String location;

    @NotNull
    private float salaryFrom;

    @NotNull
    private float salaryTo;

    @NotNull
    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @NotNull
    @Enumerated(EnumType.STRING)
    private WorkType workType;


}
