package kr.co.dm7.blackpink.security.login.service;

import kr.co.dm7.blackpink.common.security.domain.Dm7User;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;

/**
 * oauth2 를 통한 가입 혹은 로그인을 진행한다.
 *
 * @author doubleseven
 * @version 1.0
 */
public interface Oauth2Service {
    /**
     * oauth Provider 에 의해서 유저 정보를 가져온 다음에 해당 정보를 가진 유저가 있는지를 확인한다.
     * 만약 존재하지 않는다면 가입하는 과정을 진행하며 존재한다면 로그인 처리를 한다.
     *
     * @param authorizedClient
     * @param oauth2User
     * @return
     */
    Dm7User oauth2JoinOrLogin(OAuth2AuthorizedClient authorizedClient, OAuth2User oauth2User);
}
