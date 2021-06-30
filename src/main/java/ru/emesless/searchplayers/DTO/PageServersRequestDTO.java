package ru.emesless.searchplayers.DTO;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import ru.emesless.searchplayers.model.ServerSearchList;

import java.util.List;

/**
 * todo Document type PageServersRequestDTO
 */
@Data
@Getter
@Setter
public class PageServersRequestDTO {
    private List<ServerSearchList> serverSearchList;
    private int currentPage;
    private int totalPages;

    public PageServersRequestDTO(List<ServerSearchList> content, int pageNumber, int totalPages) {
        this.serverSearchList = content;
        this.currentPage = pageNumber;
        this.totalPages = totalPages;
    }
}
