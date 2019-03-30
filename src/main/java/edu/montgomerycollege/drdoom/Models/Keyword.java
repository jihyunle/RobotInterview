package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String keyword;

    @ManyToMany(mappedBy = "keywords", fetch = FetchType.LAZY)
    private Collection<JobTitle>jobTitles ;


    //=================================================
    //Constructors
    //=================================================
    //default
    public Keyword() {

    }

    //loaded
    public Keyword(String keyword){
        setKeyword(keyword);
    }

    //=================================================
    //Getters and Setters
    //=================================================
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

    public Collection<JobTitle> getJobTitles() {
        return jobTitles;
    }

    public void setJobTitles(Collection<JobTitle> jobTitles) {
        this.jobTitles = jobTitles;
    }
}
