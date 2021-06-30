package ru.emesless.searchplayers.DTO;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.emesless.searchplayers.model.FeedBack;
import ru.emesless.searchplayers.util.Views;

import java.util.List;

/**
 * todo Document type PageFeedback
 */
@Data
@Getter
@Setter
@JsonView(Views.Request.class)
public class PageFeedback {
    private List<FeedBack> feedBacks;
    private int currentPage;
    private int totalPages;

    public PageFeedback(List<FeedBack> content, int pageNumber, int totalPages) {
        this.feedBacks = content;
        this.currentPage = pageNumber;
        this.totalPages = totalPages;
    }
}
