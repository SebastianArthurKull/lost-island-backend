package ch.bbcag.lostislandbackend.api.data.repository;

import ch.bbcag.lostislandbackend.api.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findUserByName(String name);

    User findUserByEmail(String email);

    User findUserBySessionId(String sessionId);



}
