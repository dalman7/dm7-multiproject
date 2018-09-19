package kr.co.dm7.blackpink.common.security.repository;

import kr.co.dm7.blackpink.common.security.domain.ProviderUserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderUserInfoRepository extends JpaRepository<ProviderUserInfo, Long> {

}
