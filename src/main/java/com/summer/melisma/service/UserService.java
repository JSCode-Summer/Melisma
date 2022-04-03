package com.summer.melisma.service;

import com.summer.melisma.model.dto.UserDto;
import com.summer.melisma.model.entity.UserEntity;
import com.summer.melisma.model.vo.UserVo;
import com.summer.melisma.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Override
    public UserEntity loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException((username)));
    }

    public void create(UserDto dto){
        userRepository.save(UserEntity.toEntity(dto));
    }
}
