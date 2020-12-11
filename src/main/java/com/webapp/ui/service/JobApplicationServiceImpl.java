package com.webapp.ui.service;

import com.webapp.ui.model.JobApplication;
import com.webapp.ui.model.Status;
import com.webapp.ui.model.User;
import com.webapp.ui.repository.JobApplicationRepository;
import com.webapp.ui.service.base.JobApplicationService;
import com.webapp.ui.service.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobApplicationServiceImpl implements JobApplicationService {

    @Autowired
    JobApplicationRepository jobApplicationRepository;

    @Override
    public JobApplication saveJobApplication(JobApplication jobApplication) {
        if(jobApplication == null || jobApplication.getJob() == null){
            throw new NullPointerException();
        }
        return jobApplicationRepository.save(jobApplication);
    }

    @Override
    public JobApplication findJobApplicationById(int jobApplicationId) {
        return jobApplicationRepository.findById(jobApplicationId);
    }

    @Override
    public List<JobApplication> findJobApplicationsByApplicant(int userId) {
        return jobApplicationRepository.findByApplicantId(userId);
    }

    @Override
    public List<JobApplication> findJobApplicationByJobId(int jobId) {
        return jobApplicationRepository.findJobApplicationByJobId(jobId);
    }

    @Override
    public List<JobApplication> findJobApplicationsByStatus(Status status) {
        return jobApplicationRepository.findByStatus(status);
    }

    @Override
    public List<JobApplication> findByApplicantIdAndStatus(int userId, Status status) {
        return jobApplicationRepository.findByApplicantIdAndStatus(userId, status);
    }

    @Override
    public void delete(int applicationId) {
        jobApplicationRepository.deleteById(applicationId);
    }

    @Autowired
    UserService us;

    public void changeStatus(int applicationId, int statusId){
        JobApplication jobApplication = findJobApplicationById(applicationId);
        Status status = new Status(statusId, "");
        jobApplication.setStatus(status);
        if(statusId == 3){
            User user = us.findUserById(jobApplication.getApplicant().getId());
            String message = "Hello " + user.getFirst_name() + ", \n You have been accepted for job " + jobApplication.getJob().getTitle() + " at " + jobApplication.getJob().getDate();
            sendEmail(user.getEmail(), message);
        }
        saveJobApplication(jobApplication);
    }

    @Autowired
    private JavaMailSender javaMailSender;

    void sendEmail(String email, String message) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("You have been accepted for a job!");
        msg.setText(message);

        javaMailSender.send(msg);

    }
}
