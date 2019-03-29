package edu.montgomerycollege.drdoom.Services;

import edu.montgomerycollege.drdoom.Models.*;
import edu.montgomerycollege.drdoom.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
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
    JobUserRepository jobUserRepository;

    @Autowired
    JobUser_InterviewRepository jobUser_interviewRepository;

    @Autowired
    QuestionAnswerRepository qaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception
    {
//        if(roleRepository.count() == 0) {

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
                    "Admin", true, "admin");
            user.setRoles(Arrays.asList(adminRole));
            userRepository.save(user);
//        }



//            Job job;
            Job job = new Job("Java Web Developer",
                    "Java web development: Develop comprehensive application testing procedures\n" +
                    "                Update existing applications to meet the security and functionality standards as outlined in the company’s website policies\n" +
                    "                Implement testing tools that monitor the ongoing performance of the company website\n" +
                    "                Assist in updating application development policies to ensure that all future applications meet the latest technical requirements\n" +
                    "                Web Developer qualifications and skills\n" +
                    "                Next, outline the required and preferred skills for your position. \n" +
                    "            This may include education, previous job experience, certifications and technical skills. \n" +
                    "            You may also include soft skills and personality traits that you envision for a successful hire. \n" +
                    "            While it may be tempting to include a long list of skills and requirements, including too many could dissuade qualified candidates from applying. \n" +
                    "            Keep your list of qualifications concise, but provide enough detail with relevant keywords and terms.",
                    new Date(), false);
            job.setKeywords(Arrays.asList(new Keyword("Java"), new Keyword("JavaScript"), new Keyword("SpringBoot"),
                                           new Keyword("Spring")));
            jobRepository.save(job);

            job = new Job("QA", "Quality Assurance", new Date(), false);
            job.setKeywords(Arrays.asList(new Keyword("Selenium"), new Keyword("Quality Assurance")));
            jobRepository.save(job);

            job = new Job("DBA", "Database Administrator", new Date(), false);
            job.setKeywords(Arrays.asList(new Keyword("Database"), new Keyword("CRUD")));
            jobRepository.save(job);

            job = jobRepository.findByTitle("QA");

            JobUser jobUser = new JobUser(job, user, "pending interview", true);
            jobUserRepository.save(jobUser);




        QuestionAnswer qa = new QuestionAnswer("What is your prior experience?");
        qaRepository.save(qa);
        qa = new QuestionAnswer("What makes you a strong candidate for this position?");
        qaRepository.save(qa);
        qa = new QuestionAnswer("How did you hear about this company?");
        qaRepository.save(qa);

        QuestionAnswer[] qaList = new QuestionAnswer[3];

        Iterable<QuestionAnswer> questions = qaRepository.findAll();
        Iterator<QuestionAnswer> it = questions.iterator();

        // looping thru qaRepository and saving each onto the array i created in line above
        int i = 0;
        while(it.hasNext()){
            qaList[i] = it.next();
            i++;
            it.remove();
        }

<<<<<<< HEAD
        JobUser_Interview jui = new JobUser_Interview(jobUser, "2019-03-29 10:30", qaList);
=======
        jobUser = jobUserRepository.findByAppStatus("pending interview");

        JobUser_Interview jui = new JobUser_Interview(jobUser, "03/22/19 10:30", qaList);
>>>>>>> 1d1b19abc1c7d8479c8d308ec1007f1982201e17
//        jui.setJobUser(jobUser, "03/22/19 10:30", qaList);
        jui.setJobUser(jobUser);

        jobUser_interviewRepository.save(jui); // to generate the id

        // assigning jui-specific id to each qa entry
        for (int j=0; j<jui.getChatHistory().length; j++){
            jui.getChatHistory()[j].setJobUser_interview(jui);
            qaRepository.save(jui.getChatHistory()[j]);
        }

//
//        if(jobUser_interviewRepository.count()==0)
//        {
//            Interview interview = new Interview();
//            //List<Question> temp = new List<>();
//            Question temps[] = new Question[4];
//            //temps[0]=new Question("Text");
//
//            //temp.add(new Question());
//            //interview.setQuestions(Arrays.asList(temps));
//
//            Answer answer = new Answer("");
//
//            Question question = new Question("Question", answer);
//            Interview interview1=new Interview();
//
//            question.setQuestionText("What's your favorite color?");
//         question.setAnswer(new Answer("Blue"));
//
//         //questionRepository.save(question);
//            //job.setKeywords(Arrays.asList(new Keyword("Selenium"), new Keyword("Quality Assurance")));
//
//        }
        }
}
