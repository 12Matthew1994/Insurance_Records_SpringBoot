package insuranceRecords.models.services;

import insuranceRecords.models.dto.InsuredDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InsuredService {

    void create(InsuredDTO insured);

    List<InsuredDTO> getALL();

    InsuredDTO getById(long insuredId);

    void edit(InsuredDTO insured);

    void remove(long insuredId);


    InsuredDTO getByInsuranceId(long insuranceId);
}


