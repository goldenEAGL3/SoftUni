package service;

import domain.models.binding.JobBindingModel;
import domain.models.service.JobServiceModel;
import domain.models.view.JobViewModel;

import java.util.List;

public interface JobService {
    boolean add(JobBindingModel jobBindingModel);

    List<JobServiceModel> findAll();

    JobServiceModel findById(String jobId);

    void delete(String jobId);
}
