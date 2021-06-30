package ru.emesless.searchplayers.service.Users;

import ru.emesless.searchplayers.DTO.CommentsDTO;
import ru.emesless.searchplayers.repository.*;
import ru.emesless.searchplayers.security.SecurityUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.emesless.searchplayers.model.*;

import java.util.List;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    private final UserRepository userRepository;

    private final ProfileComentsRepo profileComentsRepo;

    private final ContactsRepo contactsRepo;

    private final LikesRepo likesRepo;

    private final Req_fav_repo reqFavRepo;

    private final PlayersSearchListRepository playersSearchListRepository;

    private final Serv_fav_repo servFavRepo;

    private final ServerSearchListRepo serverSearchListRepo;

    private final FeedbackRepo feedbackRepo;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository, ProfileComentsRepo profileComentsRepo, ContactsRepo contactsRepo,
        LikesRepo likesRepo, Req_fav_repo reqFavRepo, PlayersSearchListRepository playersSearchListRepository,
        Serv_fav_repo servFavRepo, ServerSearchListRepo serverSearchListRepo, FeedbackRepo feedbackRepo) {
        this.userRepository = userRepository;
        this.profileComentsRepo = profileComentsRepo;
        this.contactsRepo = contactsRepo;
        this.likesRepo = likesRepo;
        this.reqFavRepo = reqFavRepo;
        this.playersSearchListRepository = playersSearchListRepository;
        this.servFavRepo = servFavRepo;
        this.serverSearchListRepo = serverSearchListRepo;
        this.feedbackRepo = feedbackRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() ->
            new UsernameNotFoundException("User doesn't exists"));
        return SecurityUser.fromUser(user);
    }

    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() ->
            new UsernameNotFoundException("User doesn't exists"));
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void save(User user) {
        user.setStatus(Status.ACTIVE);
        if (user.getRole() == null) {
            user.setRole(Role.USER);
        }
        user.setPassword(new BCryptPasswordEncoder(12).encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void update(User user, long userId) {
        User old = getById(userId);
        user.setId(old.getId());
        user.setPassword(old.getPassword());
        user.setRole(old.getRole());
        user.setStatus(old.getStatus());
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void addComments(CommentsDTO commentsDTO) {
        ProfileComents profileComents = new ProfileComents();
        profileComents.setId(commentsDTO.getId());
        profileComents.setComments(commentsDTO.getComments());
        profileComents.setAddressesUser(getById(commentsDTO.getAddressesUser()));
        profileComents.setSenderUser(getById(commentsDTO.getSenderUser()));
        profileComentsRepo.save(profileComents);
    }

    @Override
    public List<ProfileComents> getComments(Long id) {
        return profileComentsRepo.getAllByAddressesUser_Id(id);
    }

    @Override
    public void saveContacts(Contacts contacts) {
        contactsRepo.save(contacts);
    }

    @Override
    public List<Contacts> getContacts(Long id) {
        return contactsRepo.findAllByUser(userRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public void deleteContacts(Long id) {
        contactsRepo.deleteAllByUser(userRepository.findById(id).orElse(null));
    }

    @Override
    public User getLast() {
        return userRepository.findTopByOrderByIdDesc();
    }

    @Override
    @Transactional
    public void addLike(Long id, User user) {
        Likes likes = new Likes();
        likes.setAddressesUser(getById(id));
        likes.setSenderUser(user);
        if (likesRepo.getByAddressesUserAndSenderUser(getById(id), user) == null) {
            likesRepo.save(likes);
        } else {
            likesRepo.deleteByAddressesUserAndSenderUser(getById(id), user);
        }
    }

    @Override
    @Transactional
    public void addToFavReq(Long id, User user) {
        FavoriteReqPlayers favoriteReqPlayers = new FavoriteReqPlayers();
        favoriteReqPlayers.setPlayerSearchList(playersSearchListRepository.getById(id));
        favoriteReqPlayers.setAddressesUser(user);
        if (reqFavRepo.getByAddressesUserAndPlayerSearchList_Id(user, id) == null) {
            reqFavRepo.save(favoriteReqPlayers);
        } else {
            reqFavRepo.deleteByAddressesUserAndPlayerSearchList_Id(user, id);
        }
    }

    @Override
    public List<FavoriteReqPlayers> getFavoriteReq(User user) {
        return reqFavRepo.getAllByAddressesUser(user);
    }

    @Override
    @Transactional
    public void addToFavSer(Long id, User user) {
        FavoriteServers favoriteServers = new FavoriteServers();
        favoriteServers.setAddressesUser(user);
        favoriteServers.setServerSearchList(serverSearchListRepo.getById(id));
        if (servFavRepo.getByAddressesUserAndServerSearchList_Id(user, id) == null) {
            servFavRepo.save(favoriteServers);
        } else {
            servFavRepo.deleteByAddressesUserAndServerSearchList_Id(user, id);
        }
    }

    @Override
    public List<FavoriteServers> getFavoriteSer(User user) {
        return servFavRepo.getAllByAddressesUser(user);
    }

    @Override
    public long getLikes(Long id) {
        return likesRepo.countByAddressesUser(getById(id));
    }

    @Override
    public void addFeedback(FeedBack feedBack) {
        feedbackRepo.save(feedBack);
    }
}
