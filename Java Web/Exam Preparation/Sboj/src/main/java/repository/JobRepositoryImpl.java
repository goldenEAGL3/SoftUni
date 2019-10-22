package repository;

import domain.entity.JobApplication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

public class JobRepositoryImpl implements JobRepository {
    private final EntityManager entityManager;

    @Inject
    public JobRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void save(JobApplication job) {
        this.entityManager.getTransaction().begin();
        this.entityManager.persist(job);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }

    @Override
    public List<JobApplication> findAll() {
        List<JobApplication> jobs = this.entityManager
                .createQuery("SELECT j FROM JobApplication j", JobApplication.class)
                .getResultList();
        return jobs;
    }

    @Override
    public JobApplication findById(String jobId) {
        List<JobApplication> jobs = this.entityManager
                .createQuery("SELECT j FROM JobApplication j WHERE j.id = :id", JobApplication.class)
                .setParameter("id", jobId)
                .getResultList();
        if (jobs.isEmpty()) {
            return null;
        }
        return jobs.get(0);
    }

    @Override
    public void delete(String jobId) {
        this.entityManager.getTransaction().begin();
        JobApplication job = this.findById(jobId);
        this.entityManager.remove(job);
        this.entityManager.getTransaction().commit();
        this.entityManager.close();
    }
}
