package edu.montgomerycollege.drdoom.Services;

import edu.montgomerycollege.drdoom.Models.*;
import edu.montgomerycollege.drdoom.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    KeywordRepository keywordRepository;

    @Autowired
    JobTitleRepository jobTitleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception
    {
        if(jobTitleRepository.count()==0){
            JobTitle jobTitle1=new JobTitle("Java Web developer");

            jobTitle1.setKeywords(Arrays.asList(new Keyword("Java"),new Keyword("J2EE"),
                    new Keyword("Spring boot"),new Keyword("javascript"),new Keyword("hibernate"),
                    new Keyword("Core Java"),new Keyword("Spring"),new Keyword("Jquery"),new Keyword("Struct"),new Keyword("mysql")));
            jobTitleRepository.save(jobTitle1);
            JobTitle jobTitle2=new JobTitle("DBA");

            jobTitle2.setKeywords(Arrays.asList(new Keyword("Database"),new Keyword("Designer"),
                    new Keyword("SQL"),new Keyword("Business analyst"),new Keyword("Software"),
                    new Keyword("PHP"),new Keyword("mysql"),new Keyword("C++"),new Keyword("C"),new Keyword("mysql")));
            jobTitleRepository.save(jobTitle2);
            JobTitle jobTitle3=new JobTitle("Quality Assurance");

            jobTitle3.setKeywords(Arrays.asList(new Keyword("Confidence"),new Keyword("technical skills"),
                    new Keyword("numerical skills"),new Keyword("Business analyst"),new Keyword("statistics"),
                    new Keyword("Leadership skills"),new Keyword("Planning"),new Keyword("Communication"),
                    new Keyword("Teamworking"),new Keyword("Problem-solving skills")));
            jobTitleRepository.save(jobTitle3);
            JobTitle jobTitle4=new JobTitle("Cyber Security");

            jobTitle4.setKeywords(Arrays.asList(new Keyword("security"),new Keyword("detection"),
                    new Keyword("Malware"),new Keyword("analysis"),new Keyword("mitigation"),
                    new Keyword("Cloud security"),new Keyword("Planning"),new Keyword("cybersecurity"),
                    new Keyword("linux"),new Keyword("programming")));
            jobTitleRepository.save(jobTitle4);
            JobTitle jobTitle5=new JobTitle("Assembly language");

            jobTitle4.setKeywords(Arrays.asList(new Keyword("low level"),new Keyword("C"),
                    new Keyword("C++"),new Keyword("analysis"),new Keyword("machine"),
                    new Keyword("java"),new Keyword(" cybersecurity"),new Keyword("programming"),
                    new Keyword("linux"),new Keyword("Problem-solving skills")));
            jobTitleRepository.save(jobTitle5);

        }
        if(roleRepository.count() == 0) {

            roleRepository.save(new Role("USER"));
            roleRepository.save(new Role("ADMIN"));

            Role userRole = roleRepository.findByRole("USER");
            Role adminRole = roleRepository.findByRole("ADMIN");

            User user = new User("jim@jim.com", "password", "Jim",
                    "Jimmerson", true, "jim",
                    "123-456-7890", "03/22/19 10:30", "submitted");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new User("sam@sammy.com", "password", "Sam",
                    "Sammy", true, "sam",
                    "703-456-7890", "03/21/19 16:15", "pending interview");
            user.setRoles(Arrays.asList(userRole));
            userRepository.save(user);

            user = new User("admin@admin.com", "password", "Admin",
                    "Admin", true, "admin");
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);
        }

//        if(jobRepository.count() == 0)
//        {
//            JobTitle jobTitle=new JobTitle();
//            jobTitle.getJob_title();
//            Job job = new Job(
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
//                    "3:27:2019",
//                    "false");
//
//            job.setKeywords(Arrays.asList(new Keyword("Java"), new Keyword("JavaScript"), new Keyword("SpringBoot"),
//                                           new Keyword("Spring")));
//
//            jobRepository.save(job);
//
//            job = new Job( "Quality Assurance",  "3:27:2019",
//                    "false");
//
//            job.setKeywords(Arrays.asList(new Keyword("Selenium"), new Keyword("Quality Assurance")));
//            jobRepository.save(job);
//
//            job = new Job( "Database Administrator",  "3:27:2019",
//                    "false");
//            job.setKeywords(Arrays.asList(new Keyword("Database"), new Keyword("CRUD")));
//            jobRepository.save(job);
//
//
//        }
        }
}
