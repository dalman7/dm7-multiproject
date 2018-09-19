package kr.co.dm7.blackpink.security.login.web.controller;

import kr.co.dm7.blackpink.common.security.domain.Dm7User;
import kr.co.dm7.blackpink.common.security.domain.Dm7UserRole;
import kr.co.dm7.blackpink.common.security.domain.ProviderUserInfo;
import kr.co.dm7.blackpink.security.login.service.Oauth2Service;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@Slf4j
public class Oauth2Controller {

    @Setter(onMethod = @__({@Autowired}))
    private Oauth2Service oauth2Service;

    @GetMapping("/")
    public String index(Model model,
                        @RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
                        @AuthenticationPrincipal OAuth2User oauth2User, HttpSession session) {

        System.out.println(session.getId());

        Enumeration<String> attributes = session.getAttributeNames();
        while (attributes.hasMoreElements()){
            String key = attributes.nextElement();
            System.out.println("key : " + key + ", value : "+ session.getAttribute(key));
        }


        Dm7User user = oauth2Service.oauth2JoinOrLogin(authorizedClient, oauth2User);
        List<Dm7UserRole> roles = user.getRoles();
        List<ProviderUserInfo> userInfos = user.getProviders();

        System.out.println(user.toString());
        roles.forEach(System.out::println);
        userInfos.forEach(System.out::println);

        model.addAttribute("userName", oauth2User.getName());
        model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());
        model.addAttribute("userAttributes", oauth2User.getAttributes());
        return "index";
    }
}