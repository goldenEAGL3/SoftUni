package repository;

import domain.entity.JobApplication;
import domain.models.service.JobServiceModel;

import java.util.List;

public interface JobRepository {
    void save(JobApplication job);

    List<JobApplication> findAll();

    JobApplication findById(String jobId);

    void delete(String jobId);


}
