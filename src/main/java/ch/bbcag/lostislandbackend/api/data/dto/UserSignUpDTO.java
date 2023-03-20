package ch.bbcag.lostislandbackend.api.data.dto;

import javax.validation.constraints.NotBlank;

public class UserSignUpDTO extends UserDTO{
    @NotBlank
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
