package com.website.web.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "home")
public class Home {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "text")
    private String text;
    @Column(name = "start")
    private LocalDateTime start;
    @Column(name = "stop")
    private LocalDateTime stop;
    @Column(name = "color")
    private String color;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return stop;
    }

    public void setEnd(LocalDateTime end) {
        this.stop = end;
    }
}
