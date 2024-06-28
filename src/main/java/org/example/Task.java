package org.example;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tasks")
@NamedQueries({
        @NamedQuery(name = "Task.findAll",
                query = "select t From Task t"),
        @NamedQuery(name = "Task.findByDescription",
                query = "select t from Task t where t.description like :description")
})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "heading")
    private String heading;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "target_date", nullable = false)
    private Date targetDate;

    // Default constructor required by Hibernate
    public Task() {
    }

    public Task(Date startDate, Date targetDate, String description, String status, String heading) {
        this.startDate = startDate;
        this.targetDate = targetDate;
        this.description = description;
        this.status = status;
        this.heading = heading;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public String getHeading() {
        return heading;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public void setHeading(String heading) {
        this.heading = heading;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }
}
