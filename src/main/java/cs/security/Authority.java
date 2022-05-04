package cs.security;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
    private User user;

}
