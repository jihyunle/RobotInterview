package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String keyword;

    public JobTitle getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(JobTitle jobTitle) {
        this.jobTitle = jobTitle;
    }

    //    @ManyToMany(mappedBy = "keywords", fetch = FetchType.LAZY)
//    private Collection<JobTitle>jobTitles ;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="jobTitle_id")
    private JobTitle jobTitle;
    //Constructors

    public Keyword() {

    }

    public Keyword(String keyword){
        setKeyword(keyword);
    }

    //Getters and Setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }


}
