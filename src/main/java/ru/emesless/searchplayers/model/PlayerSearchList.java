package ru.emesless.searchplayers.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.emesless.searchplayers.util.Views;

import javax.persistence.*;
import java.sql.Time;

@Data
@Entity
@Table(name = "playerSearchList")
public class PlayerSearchList {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonView(Views.Request.class)
    private Long id;

    @Column(name = "title")
    @JsonView(Views.Request.class)
    private String title;
    @Column(name = "info")
    @JsonView(Views.Request.class)
    private String info;

    @Column(name = "timeStart")
    @JsonView(Views.Request.class)
    private Time timeStart;

    @Column(name = "timeEnd")
    @JsonView(Views.Request.class)
    private Time timeEnd;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    @JsonView(Views.Request.class)
    private Game game;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonView(Views.Request.class)
    private User user;
}
