package ru.emesless.searchplayers.service.admins;

import org.springframework.data.domain.Pageable;
import ru.emesless.searchplayers.DTO.PageFeedback;
import ru.emesless.searchplayers.model.FeedBack;

/**
 * todo Document type AdminService
 */
public interface AdminService {
    void deleteReq(Long id);

    void deleteServers(Long id);

    void deleteComments(Long id);

    PageFeedback getAllFed(Pageable pageable);
}
