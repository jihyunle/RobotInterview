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
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobInterviewUserRepository jobInterviewUserRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public void run(String... strings) throws Exception
    {
//        if(roleRepository.count() == 0) {
//
//            roleRepository.save(new Role("USER"));
//            roleRepository.save(new Role("ADMIN"));
//
//            Role userRole = roleRepository.findByRole("USER");
//            Role adminRole = roleRepository.findByRole("ADMIN");
//
//            User user = new User("jim@jim.com", "password", "Jim",
//                    "Jimmerson", true, "jim");
////             User user = new User("jim@jim.com", "password", "Jim",
////                    "Jimmerson", true, "jim",
////                    "123-456-7890", "03/22/19 10:30", "submitted");
//            user.setRoles(Arrays.asList(userRole));
//            userRepository.save(user);
//
////            user = new User("sam@sammy.com", "password", "Sam",
////                    "Sammy", true, "sam",
////                    "703-456-7890", "03/21/19 16:15", "pending interview");
////            user.setRoles(Arrays.asList(userRole));
////            userRepository.save(user);
//
//            user = new User("admin@admin.com", "password", "Admin",
//                    "Admin", true, "admin");
//            user.setRoles(Arrays.asList(adminRole));
//            userRepository.save(user);
//
//            user = new User("jane@google.com", "password", "Jane",
//                            "Smith", true, "jane");
//            user.setRoles(Arrays.asList(adminRole));
//            userRepository.save(user);
//
//            user = new User("john@google.com", "password", "John",
//                            "Smith", true, "john");
//            user.setRoles(Arrays.asList(adminRole));
//            userRepository.save(user);
//        }


//        if(jobRepository.count() == 0)
//        {
////            Job job;
//            Job job = new Job("Java Web Developer",
//                    "Java web development: Develop comprehensive application testing procedures\n" +
//                    "                Update existing applications to meet the security and functionality standards as outlined in the company’s website policies\n" +
//                    "                Implement testing tools that monitor the ongoing performance of the company website\n" +
//                    "                Assist in updating application development policies to ensure that all future applications meet the latest technical requirements\n" +
//                    "                Web Developer qualifications and skills\n" +
//                    "                Next, outline the required and preferred skills for your position. \n" +
//                    "            This may include education, previous job experience, certifications and technical skills. \n" +
//                    "            You may also include soft skills and personality traits that you envision for a successful hire. \n" +
//                    "            While it may be tempting to include a long list of skills and requirements, including too many could dissuade qualified candidates from applying. \n" +
//                    "            Keep your list of qualifications concise, but provide enough detail with relevant keywords and terms.",
//                    new Date(),
//                    false);
//            job.setKeywords(Arrays.asList(new Keyword("Java"), new Keyword("JavaScript"), new Keyword("SpringBoot"),
//                                           new Keyword("Spring")));
//
//            jobRepository.save(job);
//
//            job = new Job("QA", "Quality Assurance", new Date(), false);
//            job.setKeywords(Arrays.asList(new Keyword("Selenium"), new Keyword("Quality Assurance")));
//            jobRepository.save(job);
//
//            job = new Job("DBA", "Database Administrator", new Date(), false);
//            job.setKeywords(Arrays.asList(new Keyword("Database"), new Keyword("CRUD")));
//            jobRepository.save(job);
//
//
//        }

        if(jobInterviewUserRepository.count()==0)
        {
            //Add permissions
            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            Role userRole = roleRepository.findByRole("USER");
            Role adminRole = roleRepository.findByRole("ADMIN");

            //create new jobInterviewUser instance
            JobInterviewUser jobInterviewUser = new JobInterviewUser();
                //Add job
                Job job = new Job("Job1", "Job1 Description", new Date(), false);
                job.setKeywords(Arrays.asList(new Keyword("Selenium"), new Keyword("Quality Assurance")));
                jobInterviewUser.setJob(job);
                //add user
                    User user = new User("john@google.com", "password", "John",
                                                "Smith", true, "john");
                    user.setRoles(Arrays.asList(userRole));
                    jobInterviewUser.setUser(user);
                //userRepository.save(user);
            jobInterviewUserRepository.save(jobInterviewUser);

            jobInterviewUser = new JobInterviewUser();
            //Add job
                job = new Job("Job2", "Job2 Description", new Date(), false);
                job.setKeywords(Arrays.asList(new Keyword("job2"), new Keyword("Quality Assurance")));
                jobInterviewUser.setJob(job);
                //add user
                    user = new User("sam@google.com", "password", "Sam",
                                         "Smith", true, "sam");
                    user.setRoles(Arrays.asList(userRole));
                    jobInterviewUser.setUser(user);
            //userRepository.save(user);
            jobInterviewUserRepository.save(jobInterviewUser);

            user = new User("jane@google.com", "password", "Jane",
                            "Smith", true, "jane");
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);


        //Add questions
//            Question question = new Question("What's 3x4?", new Answer(""));
            Question question = new Question("What's 3x4?", "");
            questionRepository.save(question);

            question = new Question("What's 3x5?", "");
            questionRepository.save(question);
//
//            question = new Question("What's 3x8?", new Answer(""));
//            questionRepository.save(question);
//
//            question = new Question("What's your favorite color?", new Answer(""));
//            questionRepository.save(question);
//
//            question = new Question("What's your mom's name?", new Answer(" "));
//            questionRepository.save(question);





        }


        }


}
