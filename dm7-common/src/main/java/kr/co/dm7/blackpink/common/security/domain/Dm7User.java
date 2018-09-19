package kr.co.dm7.blackpink.common.security.domain;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Dm7User : 유저의 정보를 담고 있는 Entity 다.
 * <p>
 * ---------------------------------------------------------------------------------------------------------------------
 * member       Table       field       dataType    description
 * ---------------------------------------------------------------------------------------------------------------------
 * id           Dm7User     id          String      관리를 위한 ID (PK)
 * email        Dm7User     email       String      Role 정보를 담은 id
 * firstName    Dm7User     firstName   String      User 의 first name 으로 이름이 들어간다.
 * lastName     Dm7User     lastName    String      User 의 last name 으로 성이 들어간다.
 * nickName     Dm7User     nickName    String      User 의 별명 정보가 들어간다.
 * roles        Dm7UserRole roleId      String      User 의 Role 정보들이 참조된다.
 * <p>
 * ---------------------------------------------------------------------------------------------------------------------
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Dm7User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private String nickName;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    @Builder.Default
    private List<Dm7UserRole> roles = new ArrayList<>();

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    @Builder.Default
    private List<ProviderUserInfo> providers = new ArrayList<>();

    @Transient
    private String getFullName() {
        return this.lastName + this.firstName;
    }


    public void addRole(Dm7UserRole role) {
        this.getRoles().add(role);
    }

    public void addProvider(ProviderUserInfo providerUserInfo) {
        this.getProviders().add(providerUserInfo);
    }
}