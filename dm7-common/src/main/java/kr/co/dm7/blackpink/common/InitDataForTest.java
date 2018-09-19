package kr.co.dm7.blackpink.common;

import kr.co.dm7.blackpink.common.security.domain.Dm7User;
import kr.co.dm7.blackpink.common.security.domain.Dm7UserRole;
import kr.co.dm7.blackpink.common.security.domain.ProviderUserInfo;
import kr.co.dm7.blackpink.common.security.enums.Oauth2Provider;
import kr.co.dm7.blackpink.common.security.enums.Role;
import kr.co.dm7.blackpink.common.security.repository.Dm7UserRepository;
import kr.co.dm7.blackpink.common.security.repository.Dm7UserRoleRepository;
import kr.co.dm7.blackpink.common.security.repository.ProviderUserInfoRepository;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author doubleseven
 * @version 1.0
 */
@Component
@Slf4j
//@Profile(value = "local")
public class InitDataForTest implements ApplicationRunner {

    @Setter(onMethod = @__({@Autowired}))
    private Dm7UserRepository dm7UserRepository;

    @Setter(onMethod = @__({@Autowired}))
    private Dm7UserRoleRepository dm7UserRoleRepository;

    @Setter(onMethod = @__({@Autowired}))
    private ProviderUserInfoRepository providerUserInfoRepository;

    @Override
    public void run(ApplicationArguments args) {
        Dm7User dm7User = dm7UserRepository.save(Dm7User.builder()
                .email("doubleseven@navercorp.com")
                .firstName("현선")
                .lastName("임")
                .nickName("doubleseven")
                .password("123456")
                .build());

        dm7UserRoleRepository.save(Dm7UserRole.builder()
                .roleId(Role.USER)
                .user(dm7User)
                .build());

        providerUserInfoRepository.save(ProviderUserInfo.builder()
                .provider(Oauth2Provider.Google)
                .user(dm7User)
                .build());

        Dm7User myUser = dm7UserRepository.findByEmail("doubleseven@navercorp.com").get();
        log.info(myUser.toString());

        List<Dm7UserRole> myRoles = dm7UserRoleRepository.findAll();
        myRoles.forEach(it -> log.info(it.toString()));

        List<ProviderUserInfo> providers = providerUserInfoRepository.findAll();
        providers.forEach(it -> log.info(it.toString()));
    }
}
