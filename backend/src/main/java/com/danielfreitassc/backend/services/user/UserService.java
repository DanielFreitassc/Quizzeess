package com.danielfreitassc.backend.services.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.danielfreitassc.backend.dtos.user.UserRequestDto;
import com.danielfreitassc.backend.dtos.user.UserResponseDto;
import com.danielfreitassc.backend.mappers.user.UserMapper;
import com.danielfreitassc.backend.models.user.UserEntity;
import com.danielfreitassc.backend.models.user.UserRole;
import com.danielfreitassc.backend.repositories.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto create(UserRequestDto userRequestDto) {
        if(userRepository.findByUsername(userRequestDto.username()) != null) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Usuário já cadatrado");
        String encryptedPassword =  new BCryptPasswordEncoder().encode(userRequestDto.password());
        UserEntity userEntity = userRepository.save(userMapper.toEntity(userRequestDto));
        userEntity.setRole(UserRole.USER);
        userEntity.setPassword(encryptedPassword);
        return userMapper.toDto(userRepository.save(userEntity));
        
    }

    public Page<UserResponseDto> getAllUsers(Pageable pageable, String search) {
        Page<UserEntity> users = userRepository.findAll(pageable, search);
        return users.map(userMapper::toDto);
    }

    public UserResponseDto getUserById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty()) throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado");
        return userMapper.toDto(user.get());
    }
    
    public UserResponseDto patchUser(Long id,  UserRequestDto userRequestDto) {
        Optional<UserEntity> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado;");
        
        UserEntity userEntity = userOptional.get();
        userEntity.setRole(UserRole.USER);
        
        if (userRequestDto.fullName() != null && !userRequestDto.fullName().isBlank()) {
            userEntity.setFullName(userRequestDto.fullName());
        }
        if (userRequestDto.image() != null && !userRequestDto.image().isBlank()) {
            userEntity.setImage(userRequestDto.image());
        }
        if(userRequestDto.username() != null && !userRequestDto.username().isBlank()) {
            userEntity.setUsername(userRequestDto.username());
        }
        
        if (userRequestDto.birthDate() != userOptional.get().getBirthDate()) {
            userEntity.setBirthDate(userRequestDto.birthDate());
        }

        if (userRequestDto.language() != userOptional.get().getLanguage()) {
            userEntity.setLanguage(userRequestDto.language());
        }
    
        if (userRequestDto.password() != null && !userRequestDto.password().isBlank()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(userRequestDto.password());
            userEntity.setPassword(encryptedPassword);
        }
        

        return userMapper.toDto(userRepository.save(userEntity));
    }


    public UserResponseDto delete(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isEmpty()) throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"Usuário não encontrado");
        userRepository.delete(user.get());
        return userMapper.toDto(user.get());
    }
}
