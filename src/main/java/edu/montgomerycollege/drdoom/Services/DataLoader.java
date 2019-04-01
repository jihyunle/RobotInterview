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

        if(jobUserRepository.count()==0)
        {
            //create QuestionAnswer->JobUser_Interview->JobUser
            QuestionAnswer questionAnswer = new QuestionAnswer("What are your greatest strengths?", "");
            questionAnswerRepository.save(questionAnswer);
            questionAnswer = new QuestionAnswer("Where do you see yourself in 5 years?", "");
            questionAnswerRepository.save(questionAnswer);
            questionAnswer = new QuestionAnswer("Why do you think you're a good fit for the job?", "");
            questionAnswerRepository.save(questionAnswer);

            //turn iterable into collection
            Iterable <QuestionAnswer> questionAnswers = questionAnswerRepository.findAll();
            Collection<QuestionAnswer> collection = new ArrayList<QuestionAnswer>();
            for(QuestionAnswer qa : questionAnswers)
            {
                collection.add(qa);
            }
            //create JobUser_Interview objects--don't think this is necessary, these are created when a jobUser
            // applies for a job
//            JobUser_Interview jobUser_interview = new JobUser_Interview();
//            jobUser_interview.setChatHistory(collection);
//            jobUser_interviewRepository.save(jobUser_interview);


            //create Keyword->JobTitle->Job
            //JobUser is created when a user applies to a job
            JobTitle jobTitle = new JobTitle("Java Web Developer");
            jobTitle.setKeywords(Arrays.asList(new Keyword("Java"), new Keyword("J2EE"),
                                               new Keyword("Spring boot"), new Keyword("javascript"), new Keyword("hibernate"),
                                               new Keyword("Core Java"), new Keyword("Spring"), new Keyword("Jquery"), new Keyword("Struct"), new Keyword("mysql")));
            keywordRepository.saveAll(jobTitle.getKeywords());
            jobTitle.setQuestions(collection);
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
            for(JobTitle title : jobTitleRepository.findAll())
            {
                job = new Job("This is a job description", "3/30/2019", false, "jesseberliner@gmail.com", title);
                jobRepository.save(job);
            }
        }

        /*
        if(qaRepository.count()==0)
        {
            QuestionAnswer questionAnswer = new QuestionAnswer();
            questionAnswer.setQuestion("What's your favorite color?");
            qaRepository.save(questionAnswer);

            questionAnswer = new QuestionAnswer();
            questionAnswer.setQuestion("What's your favorite food?");
            qaRepository.save(questionAnswer);

//            questionAnswer = new QuestionAnswer();
//            questionAnswer.setQuestion("What's your favorite flower? testing required 3rd q");
//            qaRepository.save(questionAnswer);

            //create a jobTitle1
            JobTitle jobTitle = new JobTitle("Java Web Developer");

            jobTitle.setKeywords(Arrays.asList(new Keyword("Java"), new Keyword("J2EE"),
                                               new Keyword("Spring boot"), new Keyword("javascript"), new Keyword("hibernate"),
                                               new Keyword("Core Java"), new Keyword("Spring"), new Keyword("Jquery"), new Keyword("Struct"), new Keyword("mysql")));
            //iterate through iterable to make it into a collection
            Collection<QuestionAnswer> questionAnswerCollection = new ArrayList<QuestionAnswer>();
            Iterable<QuestionAnswer> questions = qaRepository.findAll(); //this needs to only choose a selection of
            // the questions
            Iterator<QuestionAnswer> iterator = questions.iterator();
            while (iterator.hasNext())
            {
                questionAnswerCollection.add(iterator.next());
                iterator.remove();
            }

            jobTitle.setQuestions(questionAnswerCollection);
            jobTitleRepository.save(jobTitle);


            //second title
            JobTitle jobTitle2 = new JobTitle("DBA");
            jobTitle2.setKeywords(Arrays.asList(new Keyword("Database"), new Keyword("Designer"),
                                                new Keyword("SQL"), new Keyword("Business analyst"), new Keyword("Software"),
                                                new Keyword("PHP"), new Keyword("mysql"), new Keyword("C++"), new Keyword("C"), new Keyword("mysql")));
            jobTitle2.setQuestions(questionAnswerCollection);
            jobTitleRepository.save(jobTitle2);

            //third title
            JobTitle jobTitle3 = new JobTitle("Quality Assurance");
            jobTitle3.setKeywords(Arrays.asList(new Keyword("Confidence"), new Keyword("technical skills"),
                                                new Keyword("numerical skills"), new Keyword("Business analyst"), new Keyword("statistics"),
                                                new Keyword("Leadership skills"), new Keyword("Planning"), new Keyword("Communication"),
                                                new Keyword("Teamworking"), new Keyword("Problem-solving skills")));
            jobTitle3.setQuestions(questionAnswerCollection);
            jobTitleRepository.save(jobTitle3);

            //fourth title
            JobTitle jobTitle4 = new JobTitle("Cyber Security");
            jobTitle4.setKeywords(Arrays.asList(new Keyword("security"), new Keyword("detection"),
                                                new Keyword("Malware"), new Keyword("analysis"), new Keyword("mitigation"),
                                                new Keyword("Cloud security"), new Keyword("Planning"), new Keyword("cybersecurity"),
                                                new Keyword("linux"), new Keyword("programming")));
            jobTitle4.setQuestions(questionAnswerCollection);
            jobTitleRepository.save(jobTitle4);

            //fifth title
            JobTitle jobTitle5 = new JobTitle("Assembly Coder");
            jobTitle5.setKeywords(Arrays.asList(new Keyword("low level"), new Keyword("C"),
                                                new Keyword("C++"), new Keyword("analysis"), new Keyword("machine"),
                                                new Keyword("java"), new Keyword("cybersecurity"), new Keyword("programming"),
                                                new Keyword("linux"), new Keyword("Problem-solving skills")));
            jobTitle5.setQuestions(questionAnswerCollection);
            jobTitleRepository.save(jobTitle5);


            //create some job listings-they are not associated with users yet
            Job job = new Job("This is the job1 description", "3/31/19", false, "jesseberliner@hotmail.com", jobTitle);
            jobRepository.save(job);
            job = new Job("This is the job2 description", "3/31/19", false, "jesseberliner@hotmail.com", jobTitle2);
            jobRepository.save(job);
        }*/


    }
}
