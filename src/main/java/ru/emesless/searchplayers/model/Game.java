package ru.emesless.searchplayers.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.emesless.searchplayers.util.Views;

import javax.persistence.*;

@Data
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Request.class)
    private Long id;
    @Column(name = "title")
    @JsonView(Views.Request.class)
    private String title;
    @Column(name = "about")
    @JsonView(Views.Request.class)
    private String about;
}
