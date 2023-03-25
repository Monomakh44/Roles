package com.website.web.models;

import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "info")
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "title1")
    private String title1;
    @Column(name = "title2")
    private String title2;
    @Column(name = "title3")
    private String title3;
    @Column(name = "teg1")
    private String teg1;
    @Column(name = "teg2")
    private String teg2;
    @Column(name = "teg3")
    private String teg3;
}
