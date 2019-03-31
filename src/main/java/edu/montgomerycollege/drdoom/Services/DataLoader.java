package edu.montgomerycollege.drdoom.Services;

import edu.montgomerycollege.drdoom.Models.*;
import edu.montgomerycollege.drdoom.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    JobRepository jobRepository;
    @Autowired
    JobTitleRepository jobTitleRepository;
    @Autowired
    JobUserRepository jobUserRepository;
    @Autowired
    JobUser_InterviewRepository jobUser_interviewRepository;
    @Autowired
    KeywordRepository keywordRepository;
    @Autowired
    QuestionAnswerRepository questionAnswerRepository;
    //    @Autowired
//    ResumeRepository resumeRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {


        if (roleRepository.count() == 0) {

            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            Role userRole = roleRepository.findByRole("USER");
            Role adminRole = roleRepository.findByRole("ADMIN");

            User user = new User("jim@jim.com", "password", "Jim",
                    "Jimmerson", true, "jim",
                    "123-456-7890");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new User("sam@sammy.com", "password", "Sam",
                    "Sammy", true, "sam",
                    "703-456-7890");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new User("admin@admin.com", "password", "Admin",
                    "Admin", true, "admin", "999-999-9999");
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);


        }

        //new dataloader stuff

        if (jobUserRepository.count() == 0) {
            //create QuestionAnswer->JobUser_Interview->JobUser
            QuestionAnswer questionAnswer = new QuestionAnswer("what is programming languages?", "A programming language is a formal language that specifies a set of instructions that can be used to produce various kinds of output. Programming languages generally consist of instructions for a computer. Programming languages can be used to create programs that implement specific algorithms.");
            questionAnswerRepository.save(questionAnswer);
            questionAnswer = new QuestionAnswer("write the type of high level programming languages?", "C++ C# Cobol Fortran Java JavaScript Objective C Pascal Perl PHP Python Swift");
            questionAnswerRepository.save(questionAnswer);
            questionAnswer = new QuestionAnswer("write  basic object-oriented programming languages(OOP)concept?", "Abstraction Encapsulation inheritance polymorphism interface procedure");
            questionAnswerRepository.save(questionAnswer);
            questionAnswer = new QuestionAnswer("What is data type?", "a data type or simply type is an attribute of data which tells the compiler or interpreter how the programmer intends to use the data. Most programming languages support common data types of real, integer and boolean");
            questionAnswerRepository.save(questionAnswer);
            //turn iterable into collection
            Iterable<QuestionAnswer> questionAnswers = questionAnswerRepository.findAll();
            Collection<QuestionAnswer> collection = new ArrayList<QuestionAnswer>();
            for (QuestionAnswer qa : questionAnswers) {
                collection.add(qa);
            }
            //create JobUser_Interview objects--don't think this is necessary
//            JobUser_Interview jobUser_interview = new JobUser_Interview();
//            jobUser_interview.setChatHistory(collection);
//            jobUser_interviewRepository.save(jobUser_interview);


            //create Keyword->JobTitle->Job
            //JobUser is created when a user applies to a job
            JobTitle jobTitle = new JobTitle("Java Web Developer");
            jobTitle.setKeywords(Arrays.asList(new Keyword("Java"), new Keyword("J2EE"),
                    new Keyword("Spring boot"), new Keyword("javascript"), new Keyword("hibernate"),
                    new Keyword("Core Java"), new Keyword("Spring"), new Keyword("Jquery"), new Keyword("Struct"), new Keyword("mysql")));
            jobTitle.setQuestions(collection);
            keywordRepository.saveAll(jobTitle.getKeywords());
            // jobTitle.setQuestionAnswer(jobTitle);
            jobTitleRepository.save(jobTitle);

            jobTitle = new JobTitle("DBA");
            jobTitle.setKeywords(Arrays.asList(new Keyword("Database"), new Keyword("Designer"),
                    new Keyword("SQL"), new Keyword("Business analyst"), new Keyword("Software"),
                    new Keyword("PHP"), new Keyword("mysql"), new Keyword("C++"), new Keyword("C"), new Keyword("mysql")));
            keywordRepository.saveAll(jobTitle.getKeywords());
            jobTitle.setQuestions(collection);
            jobTitleRepository.save(jobTitle);

            //third title
            jobTitle = new JobTitle("Quality Assurance");
            jobTitle.setKeywords(Arrays.asList(new Keyword("Confidence"), new Keyword("technical skills"),
                    new Keyword("numerical skills"), new Keyword("Business analyst"), new Keyword("statistics"),
                    new Keyword("Leadership skills"), new Keyword("Planning"), new Keyword("Communication"),
                    new Keyword("Teamworking"), new Keyword("Problem-solving skills")));
            keywordRepository.saveAll(jobTitle.getKeywords());
            jobTitle.setQuestions(collection);
            jobTitleRepository.save(jobTitle);

            //fourth title
            jobTitle = new JobTitle("Cyber Security");
            jobTitle.setKeywords(Arrays.asList(new Keyword("security"), new Keyword("detection"),
                    new Keyword("Malware"), new Keyword("analysis"), new Keyword("mitigation"),
                    new Keyword("Cloud security"), new Keyword("Planning"), new Keyword("cybersecurity"),
                    new Keyword("linux"), new Keyword("programming")));
            keywordRepository.saveAll(jobTitle.getKeywords());
            jobTitle.setQuestions(collection);
            jobTitleRepository.save(jobTitle);

            //fifth title
            jobTitle = new JobTitle("Assembly Coder");
            jobTitle.setKeywords(Arrays.asList(new Keyword("low level"), new Keyword("C"),
                    new Keyword("C++"), new Keyword("analysis"), new Keyword("machine"),
                    new Keyword("java"), new Keyword("cybersecurity"), new Keyword("programming"),
                    new Keyword("linux"), new Keyword("Problem-solving skills")));
            keywordRepository.saveAll(jobTitle.getKeywords());
            jobTitle.setQuestions(collection);
            jobTitleRepository.save(jobTitle);

            Job job;
            for (JobTitle title : jobTitleRepository.findAll()) {
                job = new Job("This is a job description", "3/30/2019", false, "jesseberliner@gmail.com", title);
                jobRepository.save(job);
            }
        }

    }
}