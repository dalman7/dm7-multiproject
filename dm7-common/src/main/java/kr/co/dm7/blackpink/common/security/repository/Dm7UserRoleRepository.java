package kr.co.dm7.blackpink.common.security.repository;

import kr.co.dm7.blackpink.common.security.domain.Dm7UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Dm7UserRoleRepository extends JpaRepository<Dm7UserRole, Long> {

}
