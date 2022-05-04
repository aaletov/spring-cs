package cs.security;

import com.sun.istack.NotNull;
import cs.repos.MyUserRepository;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(access= AccessLevel.PUBLIC, force=true)
@Table(name = "Authorities")
public class Authority {

    @Id
    @NotNull
    @Column(columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String authority;

    @NotNull
    private String username;

    @Column(name = "users_username")
    private MyUser user;

    public Authority(String authority, String username, MyUser user) {
        this.authority = authority;
        this.username = username;
        this.user = user;
    }

}
