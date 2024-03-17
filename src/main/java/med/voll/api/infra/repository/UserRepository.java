package med.voll.api.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import med.voll.api.domain.user.User;

public interface UserRepository extends JpaRepository<User, Long> {

  UserDetails findByLogin(String username);

}
