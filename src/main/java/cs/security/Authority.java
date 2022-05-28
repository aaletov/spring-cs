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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String authority;

    @NotNull
    private String username;

    public Authority(String authority, String username) {
        this.authority = authority;
        this.username = username;
    }

}
