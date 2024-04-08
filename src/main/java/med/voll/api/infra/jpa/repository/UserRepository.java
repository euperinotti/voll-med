package med.voll.api.infra.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import med.voll.api.domain.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  UserDetails findByLogin(String username);

}
