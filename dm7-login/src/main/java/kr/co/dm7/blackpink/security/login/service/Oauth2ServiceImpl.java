package kr.co.dm7.blackpink.security.login.service;

import kr.co.dm7.blackpink.common.security.domain.Dm7User;
import kr.co.dm7.blackpink.common.security.domain.Dm7UserRole;
import kr.co.dm7.blackpink.common.security.domain.ProviderUserInfo;
import kr.co.dm7.blackpink.common.security.enums.Oauth2Provider;
import kr.co.dm7.blackpink.common.security.enums.Role;
import kr.co.dm7.blackpink.common.security.repository.Dm7UserRepository;
import kr.co.dm7.blackpink.common.security.repository.Dm7UserRoleRepository;
import kr.co.dm7.blackpink.common.security.repository.ProviderUserInfoRepository;
import kr.co.dm7.blackpink.security.login.enums.Oauth2ProviderAttributes;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class Oauth2ServiceImpl implements Oauth2Service {

    @Setter(onMethod = @__({@Autowired}))
    private Dm7UserRepository dm7UserRepository;

    @Setter(onMethod = @__({@Autowired}))
    private Dm7UserRoleRepository dm7UserRoleRepository;

    @Setter(onMethod = @__({@Autowired}))
    private ProviderUserInfoRepository providerUserInfoRepository;

    @Override
    public Dm7User oauth2JoinOrLogin(OAuth2AuthorizedClient authorizedClient, OAuth2User oauth2User) {

        String providerName = authorizedClient.getClientRegistration().getClientName();
        Oauth2ProviderAttributes providerAttributes = Oauth2ProviderAttributes.getOauth2ProviderAttributes(providerName);

        Dm7User user = getDm7User(providerAttributes, oauth2User);

        Optional<Dm7User> persistanceContextUser = dm7UserRepository.findByEmail(user.getEmail());
        if (persistanceContextUser.isPresent()) {
            log.info("#### 이미 존재하는 유저 정보이므로 영속컨텍스트에 있는 유저 정보를 가져옴");
            return persistanceContextUser.get();
        } else {
            log.info("#### 현재 없는 유저 정보이므로 새로운 유저를 등록하고 가져옴");
            return addDm7UserAndGet(user, providerName, oauth2User);
        }
    }

    /**
     * 새로운 Dm7User 객체를 추가하고 리턴한다.
     *
     * @param user
     * @param providerName
     * @param oauth2User
     * @return
     */
    Dm7User addDm7UserAndGet(Dm7User user, String providerName, OAuth2User oauth2User) {
        dm7UserRepository.save(user);

        Dm7UserRole role = dm7UserRoleRepository.save(Dm7UserRole.builder()
                .user(user)
                .roleId(Role.USER)
                .build());

        ProviderUserInfo provider = providerUserInfoRepository.save(ProviderUserInfo.builder()
                .provider(Oauth2Provider.valueOf(providerName))
                .providerUniqueId(oauth2User.getName())
                .user(user)
                .build());

        user.addRole(role);
        user.addProvider(provider);
        return user;
    }

    /**
     * Oauth2 를 통해 Provider 에서 얻어온 User 정보를 가져온다.
     *
     * @param providerAttributes
     * @param oauth2User
     * @return
     */
    Dm7User getDm7User(Oauth2ProviderAttributes providerAttributes, OAuth2User oauth2User) {
        return Dm7User.builder()
                .email(providerAttributes.getEmail(oauth2User))
                .firstName(providerAttributes.getFirstName(oauth2User))
                .lastName(providerAttributes.getLastName(oauth2User))
                .build();
    }
}
