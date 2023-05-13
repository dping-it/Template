package it.dping.template.model.auth;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
    @Id
    @Column(name = "id")
    @SequenceGenerator(name = "role_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;

    @Column(columnDefinition="varchar(30)", name = "name")
    private String name;

//    public Role(Long id, String name) {
//        this.id = id;
//        if (name.equals("ROLE_MODERATOR")) {
//            this.name = ERole.ROLE_MODERATOR;
//        } else if (name.equals("ROLE_ADMIN")){
//            this.name = ERole.ROLE_ADMIN;
//        }else {
//            this.name =ERole.ROLE_USER;
//        }
//    }

    //    @ManyToOne
//    @JoinColumn(name = "rol_user")
//    private User user;

}
