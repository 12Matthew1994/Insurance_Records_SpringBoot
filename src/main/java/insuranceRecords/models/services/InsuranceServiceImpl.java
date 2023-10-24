package insuranceRecords.models.services;

import insuranceRecords.data.entities.InsuranceEntity;
import insuranceRecords.data.entities.UserEntity;
import insuranceRecords.data.repositories.InsuranceRepository;
import insuranceRecords.models.dto.InsuranceDTO;
import insuranceRecords.models.dto.mapper.InsuranceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class InsuranceServiceImpl implements InsuranceService{
    @Autowired
    private InsuranceRepository insuranceRepository;

    @Autowired
    private InsuranceMapper insuranceMapper;

    @Autowired
    private InsuredService insuredService;

    @Autowired
    private UserServiceImpl userService;

    @Override
    public void create(InsuranceDTO insurance) {
        InsuranceEntity newInsurance = insuranceMapper.toEntity(insurance);

        insuranceRepository.save(newInsurance);
    }



    @Override
    public List< InsuranceDTO> getALL() {
        return StreamSupport.stream(insuranceRepository.findAll().spliterator(),false)
                .map(i -> insuranceMapper.toDTO(i))
                .toList();
    }

    @Override
    public InsuranceDTO getById(long insuranceId) {
        InsuranceEntity fetchedInsurance = insuranceRepository
                .findById(insuranceId)
                .orElseThrow();
        return insuranceMapper.toDTO(fetchedInsurance);
    }

    @Override
    public void edit( InsuranceDTO insurance) {
        InsuranceEntity fetchedInsurance = getInsuranceOrThrow(insurance.getInsuranceId());

        insuranceMapper.updateInsuranceEntity(insurance, fetchedInsurance);
        insuranceRepository.save(fetchedInsurance);
    }

    @Override
    public void remove(long insuranceId) {
        InsuranceEntity fetchedEntity = getInsuranceOrThrow(insuranceId);
        insuranceRepository.delete(fetchedEntity);
    }

    public InsuranceEntity createInsurance(InsuranceDTO insuranceDTO) {
        InsuranceEntity insurance = new InsuranceEntity();
        insurance.setInsuranceType(insuranceDTO.getInsuranceType());
        insurance.setPrice(insuranceDTO.getPrice());
        insurance.setValidFrom(insuranceDTO.getValidFrom());
        insurance.setValidTo(insuranceDTO.getValidTo());

        // Získání přihlášeného uživatele (pomocí Spring Security)
        UserEntity currentUser = userService.getLoggedInUser();
        if (currentUser != null) {
            insurance.setUser(currentUser);
        }

        return insuranceRepository.save(insurance);
    }

    private InsuranceEntity getInsuranceOrThrow(long insuranceId) {
        return insuranceRepository.findById(insuranceId)
                .orElseThrow();
    }
}

