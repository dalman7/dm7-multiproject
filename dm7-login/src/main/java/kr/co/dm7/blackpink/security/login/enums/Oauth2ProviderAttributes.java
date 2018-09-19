package kr.co.dm7.blackpink.security.login.enums;

import kr.co.dm7.blackpink.common.security.enums.Oauth2Provider;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;

public enum Oauth2ProviderAttributes {

    Google(
            Oauth2Provider.Google,
            "email",
            "given_name",
            "family_name"
    ),
    Facebook(
            Oauth2Provider.Facebook,
            "email",
            "given_name",
            "family_name"
    );

    private final String emailAttributeName;
    private final String firstNameAttributeName;
    private final String lastNameAttributeName;
    private final Oauth2Provider provider;

    Oauth2ProviderAttributes(Oauth2Provider provider, String emailAttributeName, String firstNameAttributeName, String lastNameAttributeName) {
        this.provider = provider;
        this.emailAttributeName = emailAttributeName;
        this.firstNameAttributeName = firstNameAttributeName;
        this.lastNameAttributeName = lastNameAttributeName;
    }

    /**
     * provider 문자열을 통해서 해당 Oauth2ProviderAttributes 를 얻어온다.
     *
     * @param provider
     * @return
     */
    public static Oauth2ProviderAttributes getOauth2ProviderAttributes(String provider) {
        Oauth2Provider targetProvider = Oauth2Provider.valueOf(provider);
        return Arrays.stream(Oauth2ProviderAttributes.values())
                .filter(it -> it.provider == targetProvider)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getEmail(OAuth2User oAuth2User) {
        return (String) oAuth2User.getAttributes().get(this.emailAttributeName);
    }

    public String getFirstName(OAuth2User oAuth2User) {
        return (String) oAuth2User.getAttributes().get(this.emailAttributeName);
    }

    public String getLastName(OAuth2User oAuth2User) {
        return (String) oAuth2User.getAttributes().get(this.emailAttributeName);
    }
}
