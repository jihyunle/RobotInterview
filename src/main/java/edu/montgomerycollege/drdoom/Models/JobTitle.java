package edu.montgomerycollege.drdoom.Models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Set;

@Entity
public class JobTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotNull
    private String job_title;


    @OneToMany(mappedBy = "jobTitle",cascade = CascadeType.ALL)
    private Set<Job> jobs;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name="keyword_id"), inverseJoinColumns = @JoinColumn(name="id"))
    private Collection<Keyword> keywords;

    public JobTitle() {
    }

    public JobTitle(@NotNull String job_title) {
        this.job_title = job_title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJob_title() {
        return job_title;
    }

    public void setJob_title(String job_title) {
        this.job_title = job_title;
    }

    public Set<Job> getJobs() {
        return jobs;
    }

    public void setJobs(Set<Job> jobs) {
        this.jobs = jobs;
    }
    public Collection<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(Collection<Keyword> keywords) {
        this.keywords = keywords;
    }
}
