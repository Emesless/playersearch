package ru.emesless.searchplayers.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.emesless.searchplayers.model.FeedBack;

/**
 * todo Document type FeedbackRepo
 */
public interface FeedbackRepo extends JpaRepository<FeedBack, Long> {
    void deleteById(Long id);

    Page<FeedBack> findAll(Pageable pageable);
}
