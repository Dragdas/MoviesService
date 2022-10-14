package com.kkulpa.moviesservice.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "USER_DETAILS")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DISPLAY_NAME", unique = true)
    private String displayName;

    @Column(name = "BIO")
    private String bio;

    @Column(name = "PHOTO_LINK")
    private String photoLink;


}
