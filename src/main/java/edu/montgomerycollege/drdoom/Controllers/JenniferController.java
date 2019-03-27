package edu.montgomerycollege.drdoom.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JenniferController {

    @RequestMapping("/appeal")
    public String appeal(@PathVariable("id") long id, Model model){
        return "appeal";
    }

}
