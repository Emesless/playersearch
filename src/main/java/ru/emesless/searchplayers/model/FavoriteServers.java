package ru.emesless.searchplayers.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.emesless.searchplayers.util.Views;

import javax.persistence.*;

/**
 * todo Document type FavoriteServers
 */
@Data
@Entity
@Table(name = "favorite_servers")
public class FavoriteServers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonView(Views.Request.class)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User addressesUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "server_id")
    @JsonView(Views.Request.class)
    private ServerSearchList serverSearchList;
}
