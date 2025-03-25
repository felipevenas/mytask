package com.example.mytask.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String number;
    private String username;
    private String password;

    @OneToMany(mappedBy = "user") // RELAÇÃO UM PARA MUITOS - UM USER PARA MUITAS TASKs.
    private List<Task> tasks = new ArrayList<>();

}
