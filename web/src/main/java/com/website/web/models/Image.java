package com.website.web.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "bytes")
    private byte[] bytes;
}
