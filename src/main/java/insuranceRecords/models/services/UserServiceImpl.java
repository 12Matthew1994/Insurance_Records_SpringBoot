package insuranceRecords.models.services;

import insuranceRecords.data.entities.InsuredEntity;
import insuranceRecords.data.entities.UserEntity;
import insuranceRecords.data.repositories.UserRepository;
import insuranceRecords.models.dto.InsuredDTO;
import insuranceRecords.models.dto.UserDTO;
import insuranceRecords.models.dto.mapper.InsuredMapper;
import insuranceRecords.models.exceptions.DuplicateEmailException;
import insuranceRecords.models.exceptions.PasswordsDoNotEqualException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private InsuredMapper insuredMapper;

    @Autowired
    private InsuredService insuredService;  // Přidáno

    @Override
    public UserEntity create(UserDTO user, boolean isAdmin) {
        if (!user.getPassword().equals(user.getConfirmPassword()))
            throw new PasswordsDoNotEqualException();

        UserEntity userEntity = new UserEntity();

        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setAdmin(isAdmin);

        try {

            userRepository.save(userEntity);


            InsuredEntity insuredEntity = new InsuredEntity();
            insuredEntity.setName(user.getInsuredDTO().getName());
            insuredEntity.setSurname(user.getInsuredDTO().getSurname());
            insuredEntity.setCity(user.getInsuredDTO().getCity());


            InsuredDTO insuredDTO = insuredMapper.toDTO(insuredEntity);


            insuredService.create(insuredDTO);
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException();
        }
        return userEntity;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username, " + username + " not found"));
    }

    public UserEntity getLoggedInUser() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserEntity) {
            return (UserEntity) authentication.getPrincipal();
        }
        return null;
    }
}