package cs.repos;

import cs.security.MyUser;
import org.springframework.data.repository.CrudRepository;

public interface MyUserRepository extends CrudRepository<MyUser, Integer> {
    MyUser findByUsername(String username);
}
