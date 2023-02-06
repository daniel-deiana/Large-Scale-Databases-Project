package com.example.demo.Service;


import com.example.demo.DOT.UserDTO;
import com.example.demo.Model.User;
import com.example.demo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Optional;

@Service("mainCharacterService")
@SessionAttributes("sessionVariables")
public class CharacterService {
    @Autowired
    CharacterRepository characterRepo;
}
