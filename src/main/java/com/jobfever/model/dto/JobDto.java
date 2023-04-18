package com.jobfever.model.dto;

import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.List;
@Data
public class JobDto {

    @NotEmpty(message = "Title is required.")
    private String title;

    @NotEmpty(message = "Description is required.")
    @Size(max = 2000, message = "Description cannot exceed {max} characters.")
    private String description;


    @Size(min = 1, message = "At least one technical requirement is required.")
    private List<String> technicalRequirements;

    @NotEmpty(message = "Responsibilities are required.")
    @Size(max = 2000, message = "Responsibilities cannot exceed {max} characters.")
    private String responsibilities;

    @NotEmpty(message = "Who we are looking for is required.")
    @Size(max = 2000, message = "Who we are looking for cannot exceed {max} characters.")
    private String whoWeAreLookingFor;

    @NotEmpty(message = "Benefits are required.")
    @Size(max = 2000, message = "Benefits cannot exceed {max} characters.")
    private String benefits;

    @NotEmpty(message = "Location is required.")
    private String location;

    @NotNull(message = "Salary from cannot be empty.")
    private Float salaryFrom;

    @NotNull(message = "Salary to cannot be empty.")
    private Float salaryTo;

    @NotEmpty(message = "Job type is required.")
    private String jobType;

    @NotEmpty(message = "Currency type is required.")
    private String currencyType;

    @NotEmpty(message = "Work type is required.")
    private String workType;
}
