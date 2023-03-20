package ch.bbcag.lostislandbackend.api.data.dto;

import ch.bbcag.lostislandbackend.api.data.entity.User;
import ch.bbcag.lostislandbackend.socket.PlayerData;

public class UserMapper {
    public static User fromDto(UserDTO userDTO) {
        User user = new User();
        if(userDTO instanceof UserSignUpDTO signUpDTO){
            user.setPassword(signUpDTO.getPassword());
        }
        user.setId(userDTO.getId());
        user.setSessionId(userDTO.getSessionId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setXCor(userDTO.getXCor());
        user.setYCor(userDTO.getYCor());
        user.setDirection(userDTO.getDirection());
        user.setAnimation(userDTO.getAnimation());
        user.setRole(userDTO.getRole());
        user.setMessage(userDTO.getMessage());

        return user;
    }

    public static UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setSessionId(user.getSessionId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setXCor(user.getXCor());
        userDTO.setYCor(user.getYCor());
        userDTO.setDirection(user.getDirection());
        userDTO.setAnimation(user.getAnimation());
        userDTO.setRole(user.getRole());
        userDTO.setMessage(user.getMessage());
        return userDTO;
    }



    public static User fromPersonData(PlayerData playerData){
        User user = new User();
        user.setId(playerData.getId());
        user.setName(playerData.getName());
        user.setXCor(playerData.getXcor());
        user.setYCor(playerData.getYcor());
        user.setDirection(playerData.getDirection());
        user.setAnimation(playerData.getAnimation());
        user.setRole(playerData.getRole());
        user.setMessage(playerData.getMessage());
        return user;
    }
}
