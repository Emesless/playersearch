package ru.emesless.searchplayers.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.emesless.searchplayers.util.Views;

import javax.persistence.*;

/**
 * todo Document type FeedBack
 */
@Data
@Entity
@Table(name = "feedBack")
public class FeedBack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView(Views.Request.class)
    private Long id;
    @Column(name = "title")
    @JsonView(Views.Request.class)
    private String title;
    @Column(name = "info")
    @JsonView(Views.Request.class)
    private String info;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonView(Views.Request.class)
    private User user;
}
