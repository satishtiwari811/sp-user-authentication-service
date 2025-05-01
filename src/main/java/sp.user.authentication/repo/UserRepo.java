package sp.user.authentication.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sp.user.authentication.entity.UserAuth;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserAuth,Long> {
    UserAuth save(UserAuth userAuth);
    Optional<UserAuth> findByUserName(String userName);
    //rAuth findByUseNameAndPasswordAndRole(String userName, String password, String role);
}
