package kr.co.dm7.blackpink.common.security.domain;

import kr.co.dm7.blackpink.common.security.enums.Role;
import lombok.*;

import javax.persistence.*;

/**
 * Dm7UserRole : 유저의 Role 정보를 담고 있는 Entity 다.
 * <p>
 * ---------------------------------------------------------------------------------------------------------------------
 * member       Table       field       dataType    description
 * ---------------------------------------------------------------------------------------------------------------------
 * id           DmUserRole  id          String      관리를 위한 ID (PK)
 * roleId       DmUserRole  String      String      Role 정보를 담은 id
 * userId       DmUser      id          String      User 의 id
 * ---------------------------------------------------------------------------------------------------------------------
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "user")
public class Dm7UserRole extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Builder.Default
    private Role roleId = Role.USER;

    @ManyToOne(optional = false)
    @JoinColumn(name = "userId")
    private Dm7User user;
}