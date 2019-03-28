package edu.montgomerycollege.drdoom.Models;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Entity
@Table(name="USER_DATA")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;

    @Column(name="email", nullable = false)
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="enabled")
    private boolean enabled;

    @Column(name="username")
    private String username;

    @Column(name = "userpicture")
    private String userpicture;

    // additions from former Applicant class
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "interview_time")
    private LocalDateTime interviewTime; //jen uses localdatetime, jesse - date

    @Column(name = "application_status")
    private String appStatus;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = @JoinColumn(name="role_id"))
    private Collection<Role> roles;

    @OneToMany(mappedBy = "id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<JobUser> jobUsers;

    public Set<JobUser> getJobUsers() {
        return jobUsers;
    }

    public void setJobUsers(Set<JobUser> jobUsers) {
        this.jobUsers = jobUsers;
    }
//    @OneToMany(mappedBy = "id",
//            cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<JobInterviewUser> jobInterviewUsers;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "jobInterviewUser_id")
//    private JobInterviewUser jobInterviewUser;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Resume> resumes;


    public User() {

    }

    public User(String email, String password, String firstName,
                String lastName, boolean enabled, String username,
                String phoneNumber, String interviewTime, String appStatus)
    {
        this.setEmail(email);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEnabled(enabled);
        this.setUsername(username);
        this.setPhoneNumber(phoneNumber);
        this.setInterviewTime(interviewTime);
        this.setAppStatus(appStatus);
    }

    public User(String email, String password, String firstName,
                String lastName, boolean enabled, String username)
    {
        this.setEmail(email);
        this.setPassword(password);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setEnabled(enabled);
        this.setUsername(username);
    }


    //Getters and setters

    public long getUserId()
    {
        return userId;
    }

    public void setUserId(long userId)
    {
        this.userId = userId;
    }


//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (isEmailValid(email)){
            this.email = email;
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password=passwordEncoder.encode(password);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpicture() {
        return userpicture;
    }

    public void setUserpicture(String userpicture) {
        this.userpicture = userpicture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (isPhoneNumberValid(phoneNumber)){
            this.phoneNumber = phoneNumber;
        }
    }

    public LocalDateTime getInterviewTime() {
        return interviewTime;
    }

    public void setInterviewTime(String interviewTime) {
        DateTimeFormatter f = DateTimeFormatter.ofPattern("MM/dd/yy hh:mm");
        this.interviewTime = LocalDateTime.parse(interviewTime, f);
    }

    public String getAppStatus() {
        return appStatus;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

//    public Set<JobInterviewUser> getJobInterviewUsers()
//    {
//        return jobInterviewUsers;
//    }
//
//    public void setJobInterviewUsers(Set<JobInterviewUser> jobInterviewUsers)
//    {
//        this.jobInterviewUsers = jobInterviewUsers;
//    }

    //    public JobInterviewUser getJobInterviewUser() {
//        return jobInterviewUser;
//    }
//
//    public void setJobInterviewUser(JobInterviewUser jobInterviewUser) {
//        this.jobInterviewUser = jobInterviewUser;
//    }

    public Set<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(Set<Resume> resumes) {
        this.resumes = resumes;
    }

    private boolean isEmailValid(String email){
        boolean isValid = false;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;
        //Make the comparison case-insensitive.
        Pattern pattern = Pattern.compile(expression,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches()){
            isValid = true;
        }
        return isValid;
    }

    private boolean isPhoneNumberValid(String phoneNumber){
        boolean isValid = false;
        String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$";
        CharSequence inputStr = phoneNumber;
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(inputStr);
        if(matcher.matches()){
            isValid = true;
        }
        return isValid;
    }
}
