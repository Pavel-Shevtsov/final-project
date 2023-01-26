package com.online.diary.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;



@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false,unique = true)
    private String eMail;
    @Column(nullable = false)
    private String role;
    @Column()
    private LocalDate birthday;
    @Lob
    @Column
    private byte[] image;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<MomentsOfLife> momentsOfLives;

}


