package kr.megaptera.makaogift.repositories;

import kr.megaptera.makaogift.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUserId(String userId);

  User getByUserId(String userId);
}
