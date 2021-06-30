package ru.emesless.searchplayers.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.emesless.searchplayers.util.Views;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @JsonView(Views.UserId.class)
    private Long id;
    @Column(name = "email")

    private String email;
    @Column(name = "password")
    private String password;
    @JsonView(Views.UserInfo.class)
    @Column(name = "first_name")
    private String firstName;
    @JsonView(Views.UserInfo.class)
    @Column(name = "last_name")
    private String lastName;

    @JsonView(Views.UserInfo.class)
    @Column(name = "nickname")
    private String nickname;
    @JsonView(Views.UserInfo.class)
    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Enumerated(value = EnumType.STRING)
    @JsonView(Views.UserRole.class)
    @Column(name = "role")
    private Role role;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private Status status;
}
