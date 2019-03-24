package com.surprise.am.model;


import com.surprise.am.model.user_enum.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private int age;
    @Column
    @Enumerated(EnumType.STRING)
    private Gender gender= Gender.FEMALE;
    @Column
    private String email;
    @Column
    private String password;
    @Column(name = "image_path")
    private String imagePath;

}