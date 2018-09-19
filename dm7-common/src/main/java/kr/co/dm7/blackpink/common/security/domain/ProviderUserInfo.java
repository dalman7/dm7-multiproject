package kr.co.dm7.blackpink.common.security.domain;

import kr.co.dm7.blackpink.common.security.enums.Oauth2Provider;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "user")
public class ProviderUserInfo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private Oauth2Provider provider;

    private String providerUniqueId;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    private Dm7User user;
}
