package com.jeanlima.springmvcdatajpaapp.service;

import com.jeanlima.springmvcdatajpaapp.model.Avatar;
import com.jeanlima.springmvcdatajpaapp.repository.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AvatarService {
    @Autowired
    AvatarRepository avatarRepository;

    public List<Avatar> getAvatares(){
        return avatarRepository.findAll();
    }

    public void createAvatar(Avatar avatar) { avatarRepository.save(avatar); }
}
