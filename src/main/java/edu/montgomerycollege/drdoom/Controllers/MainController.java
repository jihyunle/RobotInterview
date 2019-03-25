package edu.montgomerycollege.drdoom.Controllers;


import edu.montgomerycollege.drdoom.Models.User;
import edu.montgomerycollege.drdoom.Services.CustomUserDetails;
import edu.montgomerycollege.drdoom.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.Map;

@Controller
public class MainController
{


    @Autowired
    private UserService userService;


    @RequestMapping({"/index","/"})
    public String welcomePage()
    {
        return "index";
    }

    @RequestMapping({"/jobs"})
    public String allJobs()
    {

        return "jobs";
    }

    @RequestMapping({"/myjobs"})
    public String myJobs()
    {
        return "myjobs";
    }

    @RequestMapping({"/apply"})
    public String apply()
    {
        //add Job to my jobs
        return "myjobs";
    }





}
