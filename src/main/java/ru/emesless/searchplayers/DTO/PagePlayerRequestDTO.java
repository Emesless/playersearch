package ru.emesless.searchplayers.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.emesless.searchplayers.model.PlayerSearchList;

import java.util.List;

/**
 * todo Document type PagePlayerRequest
 */

@Data
@Getter
@Setter
public class PagePlayerRequestDTO {
    private List<PlayerSearchList> playerSearchList;
    private int currentPage;
    private int totalPages;

    public PagePlayerRequestDTO(List<PlayerSearchList> content, int pageNumber, int totalPages) {
        this.playerSearchList = content;
        this.currentPage = pageNumber;
        this.totalPages = totalPages;
    }
}
