package by.home.jarward.jar.repository.interfaces;

import by.home.jarward.jar.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {
}
