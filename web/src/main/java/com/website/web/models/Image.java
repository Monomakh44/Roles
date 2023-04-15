package com.website.web.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "image")
public class Image {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "bytes")
    private byte[] bytes;

    @OneToOne(mappedBy = "image")
    private Users users;
}
