package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.AdDto;
import ru.skypro.homework.dto.CreateOrUpdateAdDto;
import ru.skypro.homework.dto.ExtendedAdDto;
import ru.skypro.homework.entity.AdEntity;
import ru.skypro.homework.entity.ImageEntity;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AdMapper {

    public AdDto toAdDto(AdEntity entity) {
        if (entity == null) {
            return null;
        }
        return AdDto.builder()
                .author(entity.getAuthor().getId())
                .image(getImageUrls(entity.getImages()))
                .pk(entity.getId())
                .price(entity.getPrice())
                .title(entity.getTitle())
                .build();
    }

    public ExtendedAdDto toExtendedAdDto(AdEntity entity) {
        if (entity == null) {
            return null;
        }
        return ExtendedAdDto.builder()
                .pk(entity.getId())
                .authorFirstName(entity.getAuthor().getFirstName())
                .authorLastName(entity.getAuthor().getLastName())
                .description(entity.getDescription())
                .email(entity.getAuthor().getEmail())
                .image(getImageUrls(entity.getImages()))
                .phone(entity.getAuthor().getPhone())
                .price(entity.getPrice())
                .title(entity.getTitle())
                .build();
    }


    public AdEntity toEntity(CreateOrUpdateAdDto dto) {
        if (dto == null) {
            return null;
        }
        return AdEntity.builder()
                .title(dto.getTitle())
                .price(dto.getPrice())
                .description(dto.getDescription())
                .build();
    }


    public void updateEntity(AdEntity entity, CreateOrUpdateAdDto dto) {
        if (entity == null || dto == null) {
            return;
        }
        entity.setTitle(dto.getTitle());
        entity.setPrice(dto.getPrice());
        entity.setDescription(dto.getDescription());
    }

    public List<AdDto> toAdDtoList(List<AdEntity> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream()
                .map(this::toAdDto)
                .collect(Collectors.toList());
    }

    private List<String> getImageUrls(List<ImageEntity> images) {
        if (images == null || images.isEmpty()) {
            return List.of();
        }
        return images.stream()
                .map(img -> "/images/" + img.getId())
                .collect(Collectors.toList());
    }
}