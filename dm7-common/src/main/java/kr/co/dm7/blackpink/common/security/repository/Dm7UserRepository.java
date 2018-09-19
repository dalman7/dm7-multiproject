package kr.co.dm7.blackpink.common.security.repository;

import kr.co.dm7.blackpink.common.security.domain.Dm7User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Dm7UserRepository extends JpaRepository<Dm7User, Long> {

    Optional<Dm7User> findByEmail(String email);
}