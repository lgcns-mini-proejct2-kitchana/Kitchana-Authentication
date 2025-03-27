package com.kitcha.authentication.service;

import com.kitcha.authentication.dto.InterestDto;
import com.kitcha.authentication.entity.UserEntity;
import com.kitcha.authentication.exception.EmailNotFoundException;
import com.kitcha.authentication.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InterestService {
    private final UserRepository userRepository;

    public void setInterest(String email, InterestDto dto) throws RuntimeException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new EmailNotFoundException("올바른 사용자가 아닙니다.");
        }

        userEntity.setInterest(dto.getInterest());
        userRepository.save(userEntity);
    }

    public String getInterest(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new EmailNotFoundException("올바른 사용자가 아닙니다.");
        }

        return userEntity.getInterest();
    }
}
