package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long kid;

    private String kword;

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

    public Keyword(String kword) {
        this.kword = kword;
    }


    //Getters and Setters
    public long getKid() {
        return kid;
    }

    public void setKid(long kid) {
        this.kid = kid;
    }

    public String getKword() {
        return kword;
    }

    public void setKword(String kword) {
        this.kword = kword;
    }


}
