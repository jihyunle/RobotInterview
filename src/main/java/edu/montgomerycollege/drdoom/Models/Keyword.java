package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long kid;

    private String kword;

    @ManyToMany(mappedBy = "keywords", fetch = FetchType.LAZY)
    private Collection<Job> jobs;

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

    public Collection<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Collection<Job> jobs) {
        this.jobs = jobs;
    }
}
