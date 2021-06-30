package ru.emesless.searchplayers.service.admins;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.emesless.searchplayers.DTO.PageFeedback;
import ru.emesless.searchplayers.model.FeedBack;
import ru.emesless.searchplayers.repository.FeedbackRepo;
import ru.emesless.searchplayers.repository.PlayersSearchListRepository;
import ru.emesless.searchplayers.repository.ProfileComentsRepo;
import ru.emesless.searchplayers.repository.ServerSearchListRepo;

/**
 * todo Document type AdminDetailsDervice
 */
@Service
public class AdminDetailsDervice implements AdminService {

    private final PlayersSearchListRepository playersSearchListRepository;
    private final ServerSearchListRepo serverSearchListRepo;
    private final FeedbackRepo feedbackRepo;
    private final ProfileComentsRepo profileComentsRepo;

    @Autowired
    public AdminDetailsDervice(PlayersSearchListRepository playersSearchListRepository,
        ServerSearchListRepo serverSearchListRepo, FeedbackRepo feedbackRepo, ProfileComentsRepo profileComentsRepo) {
        this.playersSearchListRepository = playersSearchListRepository;
        this.serverSearchListRepo = serverSearchListRepo;
        this.feedbackRepo = feedbackRepo;
        this.profileComentsRepo = profileComentsRepo;
    }

    @Override
    @Transactional
    public void deleteReq(Long id) {
        playersSearchListRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteServers(Long id) {
        serverSearchListRepo.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteComments(Long id) {
        profileComentsRepo.deleteById(id);
    }

    @Override
    public PageFeedback getAllFed(Pageable pageable) {
        Page<FeedBack> page = feedbackRepo.findAll(pageable);
        return new PageFeedback(page.getContent(),
            pageable.getPageNumber(),
            page.getTotalPages());
    }
}
