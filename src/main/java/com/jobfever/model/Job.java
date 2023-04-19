package com.jobfever.model;
import com.jobfever.model.enums.CurrencyType;
import com.jobfever.model.enums.JobType;
import com.jobfever.model.enums.WorkType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "jobs")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;

//    @NotEmpty
    private String title;

//    @NotEmpty
//    @Column(length = 2000)
    private String description;

    @ElementCollection
    @Size(min = 1)
    private List<String> technicalRequirements;

    @ElementCollection
    private List<Integer> candidateIds;

//    @NotEmpty
//    @Column(length = 2000)
    private String responsibilities;

//    @NotEmpty
//    @Column(length = 2000)
//    @Size(max = 2000)
    private String whoWeAreLookingFor;

//    @NotEmpty
//    @Column(length = 2000)
//    @Size(max = 2000)
    private String benefits;

//    @NotEmpty
    private String location;

//    @DecimalMin("0.0")
    private float salaryFrom;


//    @DecimalMax("100000.0")
    private float salaryTo;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @Enumerated(EnumType.STRING)
    private WorkType workType;

    private LocalDateTime postingDate = LocalDateTime.now();

    @ManyToOne
    private Employer employer;


}
