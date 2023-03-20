package ch.bbcag.lostislandbackend.api.service;

import ch.bbcag.lostislandbackend.api.data.dto.UserDTO;
import ch.bbcag.lostislandbackend.api.data.dto.UserMapper;
import ch.bbcag.lostislandbackend.api.data.dto.UserSignUpDTO;
import ch.bbcag.lostislandbackend.api.data.entity.User;
import ch.bbcag.lostislandbackend.api.data.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO getById(int id){
        User user = userRepository.getReferenceById(id);
        return UserMapper.toDTO(user);
    }

    public UserDTO getByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        return UserMapper.toDTO(user);
    }

    public UserDTO getBySessionId(String sessionId){
        User user = userRepository.findUserBySessionId(sessionId);
        return UserMapper.toDTO(user);
    }

    public List<UserDTO> getAll() {
       List<User> users = userRepository.findAll();
       List<UserDTO> userDTOS = new ArrayList<>();
       users.forEach(user -> userDTOS.add(UserMapper.toDTO(user)));
       return userDTOS;
    }

    public UserDTO create(UserSignUpDTO userSignUpDTO){
        User user = UserMapper.fromDto(userSignUpDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setMessage("message");
        user.setAnimation("idle");
        user.setDirection("right");
        user.setName("name");
        user.setRole("Archer-Green");
        user.setXCor(0);
        user.setYCor(0);
        user = userRepository.save(user);
        return UserMapper.toDTO(user);
    }

    public UserDTO update(UserDTO changingUserDTO, int id){
        User changingUser = UserMapper.fromDto(changingUserDTO);
        Optional<User> optionalUser = userRepository.findById(id);
        User actualUser = optionalUser.get();

        mergeUser(changingUser, actualUser);
        return UserMapper.toDTO(userRepository.save(actualUser));
    }

    public void mergeUser(User changingUser, User actualUser){

        if (changingUser.getSessionId() != null){
            actualUser.setSessionId(changingUser.getSessionId());
        }
        if (changingUser.getName() != null){
            actualUser.setName(changingUser.getName());
        }
        if (changingUser.getXCor() != null){
            actualUser.setXCor(changingUser.getXCor());
        }
        if (changingUser.getYCor() != null){
            actualUser.setYCor(changingUser.getYCor());
        }
        if (changingUser.getRole() != null){
            actualUser.setRole(changingUser.getRole());
        }
        if (changingUser.getDirection() != null){
            actualUser.setDirection(changingUser.getDirection());
        }
        if (changingUser.getAnimation() != null){
            actualUser.setAnimation(changingUser.getAnimation());
        }
        if (changingUser.getMessage() != null){
            actualUser.setMessage(changingUser.getMessage());
        }
        if (changingUser.getEmail() != null){
            actualUser.setEmail(changingUser.getEmail());
        }

    }

    public void deleteById(Integer id){
        userRepository.deleteById(id);
    }

}
