package it.dping.template.repository;


import it.dping.template.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
    User getByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);

}
