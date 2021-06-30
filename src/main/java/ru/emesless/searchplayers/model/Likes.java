package ru.emesless.searchplayers.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.emesless.searchplayers.util.Views;

import javax.persistence.*;

/**
 * todo Document type likes
 */
@Data
@Entity
@Table(name = "likes")
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Request.class)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_addresses")
    @JsonView(Views.Request.class)
    private User addressesUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_sender")
    @JsonView(Views.Request.class)
    private User senderUser;
}

