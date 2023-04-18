package com.jobfever.model;
import com.jobfever.model.enums.CurrencyType;
import com.jobfever.model.enums.JobType;
import com.jobfever.model.enums.WorkType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

////    @NotEmpty
//    private String title;
//
////    @NotEmpty
////    @Column(length = 2000)
//    private String description;
//
//    @ElementCollection
//    @Size(min = 1)
//    private List<String> technicalRequirements;
//
////    @NotEmpty
////    @Column(length = 2000)
//    private String responsibilities;
//
////    @NotEmpty
////    @Column(length = 2000)
////    @Size(max = 2000)
//    private String whoWeAreLookingFor;
//
////    @NotEmpty
////    @Column(length = 2000)
////    @Size(max = 2000)
//    private String benefits;
//
////    @NotEmpty
//    private String location;
//
////    @DecimalMin("0.0")
//    private float salaryFrom;
//
//
////    @DecimalMax("100000.0")
//    private float salaryTo;
//
//    @Enumerated(EnumType.STRING)
//    private JobType jobType;
//
//    @Enumerated(EnumType.STRING)
//    private CurrencyType currencyType;
//
//    @Enumerated(EnumType.STRING)
//    private WorkType workType;
//
    private LocalDateTime postingDate = LocalDateTime.now();

    @ManyToOne
    private Employer employer;


    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Description is required")
    @Size(max = 2000, message = "Description cannot exceed {max} characters")
    private String description;

    @ElementCollection
    @Size(min = 1, message = "At least one technical requirement is required")
    private List<String> technicalRequirements;

    @NotEmpty(message = "Responsibilities are required")
    @Size(max = 2000, message = "Responsibilities cannot exceed {max} characters")
    private String responsibilities;

    @NotEmpty(message = "Who we are looking for is required")
    @Size(max = 2000, message = "Who we are looking for cannot exceed {max} characters")
    private String whoWeAreLookingFor;

    @NotEmpty(message = "Benefits are required")
    @Size(max = 2000, message = "Benefits cannot exceed {max} characters")
    private String benefits;

    @NotEmpty(message = "Location is required")
    private String location;

    @NotNull(message = "Salary from cannot be empty")
    private float salaryFrom;

    @NotNull(message = "Salary to cannot be empty")
    private float salaryTo;

    @NotNull(message = "Job type is required")
    @Enumerated(EnumType.STRING)
    private JobType jobType;

    @NotNull(message = "Currency type is required")
    @Enumerated(EnumType.STRING)
    private CurrencyType currencyType;

    @NotNull(message = "Work type is required")
    @Enumerated(EnumType.STRING)
    private WorkType workType;


}
