package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.CommentDto;
import ru.skypro.homework.dto.CreateOrUpdateCommentDto;
import ru.skypro.homework.entity.CommentEntity;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CommentMapper {


    public CommentDto toDto(CommentEntity entity) {
        if (entity == null) {
            return null;
        }
        String authorImage = entity.getAuthor().getImage() != null
                ? "/images/" + entity.getAuthor().getImage().getId()
                : null;

        return CommentDto.builder()
                .author(entity.getAuthor().getId())
                .authorImage(authorImage)
                .authorFirstName(entity.getAuthor().getFirstName())
                .createdAt(entity.getCreatedAt())
                .pk(entity.getId())
                .text(entity.getText())
                .build();
    }


    public CommentEntity toEntity(CreateOrUpdateCommentDto dto) {
        if (dto == null) {
            return null;
        }
        return CommentEntity.builder()
                .text(dto.getText())
                .createdAt(System.currentTimeMillis())
                .build();
    }


    public void updateEntity(CommentEntity entity, CreateOrUpdateCommentDto dto) {
        if (entity == null || dto == null) {
            return;
        }
        entity.setText(dto.getText());
    }


    public List<CommentDto> toDtoList(List<CommentEntity> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}