package insuranceRecords.models.services;

import insuranceRecords.data.entities.UserEntity;
import insuranceRecords.models.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserEntity create(UserDTO user, boolean isAdmin);




}
