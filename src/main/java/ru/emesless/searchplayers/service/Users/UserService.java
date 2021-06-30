package ru.emesless.searchplayers.service.Users;

import ru.emesless.searchplayers.DTO.CommentsDTO;
import ru.emesless.searchplayers.model.*;

import java.util.List;

public interface UserService {
    User getById(Long id);

    void update(User user, long userId);

    void save(User user);

    void delete(Long id);

    List<User> getAll();

    User loadUserByEmail(String email);

    void addComments(CommentsDTO commentsDTO);

    List<ProfileComents> getComments(Long id);

    void saveContacts(Contacts contacts);

    User getLast();

    List<Contacts> getContacts(Long id);

    void deleteContacts(Long id);

    void addLike(Long id, User user);

    long getLikes(Long id);

    void addToFavReq(Long id, User user);

    void addToFavSer(Long id, User user);

    List<FavoriteServers> getFavoriteSer(User user);

    List<FavoriteReqPlayers> getFavoriteReq(User user);

    void addFeedback(FeedBack feedBack);
}
