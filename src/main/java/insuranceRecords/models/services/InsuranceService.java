package insuranceRecords.models.services;

import insuranceRecords.data.entities.InsuranceEntity;
import insuranceRecords.models.dto.InsuranceDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InsuranceService {

    void create(InsuranceDTO insurance);

    List<InsuranceDTO> getALL();

    InsuranceDTO getById(long insuranceId);

    void edit(InsuranceDTO insurance);

    void remove(long insuranceId);


    InsuranceEntity createInsurance(InsuranceDTO insurance);


}
