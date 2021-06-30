package ru.emesless.searchplayers.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import ru.emesless.searchplayers.util.Views;

import javax.persistence.*;

/**
 * todo Document type FavoriteReqPlayers
 */
@Data
@Entity
@Table(name = "favorite_req_players")
public class FavoriteReqPlayers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User addressesUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "players_req_id")
    @JsonView(Views.Request.class)
    private PlayerSearchList playerSearchList;
}
