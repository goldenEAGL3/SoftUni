package service;

import domain.entity.JobApplication;
import domain.entity.enums.Sector;
import domain.models.binding.JobBindingModel;
import domain.models.service.JobServiceModel;
import domain.models.service.UserServiceModel;
import org.modelmapper.ModelMapper;
import repository.JobRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;


public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public JobServiceImpl(JobRepository jobRepository, UserService userService, ModelMapper modelMapper) {
        this.jobRepository = jobRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean add(JobBindingModel jobBindingModel) {
        if (invalidData(jobBindingModel)) {
            return false;
        }

        Sector sector = Sector.valueOf(jobBindingModel.getSector());
        UserServiceModel userServiceModel = this.userService.findByUsername(jobBindingModel.getUser());
        JobServiceModel job = this.modelMapper.map(jobBindingModel, JobServiceModel.class);
        job.setUser(userServiceModel);
//        job.setSector(sector);

        JobApplication newJob = this.modelMapper.map(job, JobApplication.class);
        this.jobRepository.save(newJob);
        return true;

    }

    @Override
    public List<JobServiceModel> findAll() {
        List<JobServiceModel> jobs = this.jobRepository.findAll()
                .stream()
                .map(job -> modelMapper.map(job, JobServiceModel.class))
                .collect(Collectors.toList());
        return jobs;
    }

    @Override
    public JobServiceModel findById(String jobId) {
        JobApplication job = this.jobRepository.findById(jobId);
        JobServiceModel desiredJob = this.modelMapper.map(job, JobServiceModel.class);
        return desiredJob;
    }

    @Override
    public void delete(String jobId) {
        this.jobRepository.delete(jobId);
    }

    private boolean invalidData(JobBindingModel jobBindingModel) {
        return jobBindingModel.getSector() == null ||
                jobBindingModel.getProfession() == null ||
                jobBindingModel.getDescription() == null ||
                jobBindingModel.getSalary() == null ||
                jobBindingModel.getUser() == null;
    }

}
