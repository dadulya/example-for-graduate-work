package ru.skypro.homework.mapper;

import org.springframework.stereotype.Component;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.ImageEntity;
import ru.skypro.homework.entity.UserEntity;


@Component
public class UserMapper {


    public UserDto toDto(UserEntity entity) {
        if (entity == null) {
            return null;
        }
        return UserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .phone(entity.getPhone())
                .role(entity.getRole())
                .image(getImageUrl(entity.getImage()))
                .build();
    }


    public UserEntity toEntity(Register dto) {
        if (dto == null) {
            return null;
        }
        return UserEntity.builder()
                .email(dto.getUsername())
                .password(dto.getPassword())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phone(dto.getPhone())
                .role(dto.getRole() != null ? dto.getRole().name() : "USER")
                .build();
    }

    public void updateEntity(UserEntity entity, UpdateUserDto dto) {
        if (entity == null || dto == null) {
            return;
        }
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setPhone(dto.getPhone());
    }


    private String getImageUrl(ImageEntity image) {
        if (image == null) {
            return null;
        }
        return "/images/" + image.getId();
    }
}
