package ru.emesless.searchplayers.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * todo Document type ChatMessages
 */
@Data
@Entity
@Table(name = "chatMessages")
public class ChatMessages {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "message")
    private String message;
    @JsonIgnoreProperties({"password"})
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
