package by.home.jarward.jar.repository.interfaces;

import by.home.jarward.jar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    Optional<User> findByLogin(String login);
    Optional<User> findByEmail(String email);
    List<User> findByEnabledFalseAndDateTimeCreateLessThan(LocalDateTime dateTimeCreate);

}
