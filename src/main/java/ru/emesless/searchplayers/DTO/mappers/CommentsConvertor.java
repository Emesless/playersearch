package ru.emesless.searchplayers.DTO.mappers;

import ru.emesless.searchplayers.DTO.TesTDTO;
import ru.emesless.searchplayers.model.ProfileComents;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

/**
 * todo Document type CommentsConvertor
 */
@Component
public class CommentsConvertor {
    private final ModelMapper modelMapper;

    public CommentsConvertor() {
        this.modelMapper = new ModelMapper();
    }

    ;

    public TesTDTO convertToDto(ProfileComents profileComents) {
        return modelMapper.map(profileComents, TesTDTO.class);
    }
}
