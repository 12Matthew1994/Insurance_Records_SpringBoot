package insuranceRecords.models.services;

import insuranceRecords.data.entities.InsuredEntity;
import insuranceRecords.data.repositories.InsuredRepository;
import insuranceRecords.models.dto.InsuredDTO;
import insuranceRecords.models.dto.mapper.InsuredMapper;
import insuranceRecords.models.exceptions.InsuredNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
public class InsuredServiceImpl implements InsuredService {

    @Autowired
    private InsuredRepository insuredRepository;

    @Autowired
    private InsuredMapper insuredMapper;

    @Override
    public void create(@Valid InsuredDTO insured) {
        InsuredEntity newInsured = insuredMapper.toEntity(insured);

        insuredRepository.save(newInsured);
    }

    @Override
    public List<InsuredDTO> getALL() {
        return StreamSupport.stream(insuredRepository.findAll().spliterator(),false)
                .map(i -> insuredMapper.toDTO(i))
                .toList();
    }

    @Override
    public InsuredDTO getById(long insuredId) {
        InsuredEntity fetchedInsured = insuredRepository
                .findById(insuredId)
                .orElseThrow();
        return insuredMapper.toDTO(fetchedInsured);
    }

    @Override
    public void edit(InsuredDTO insured) {
        InsuredEntity fetchedInsured = getInsuredOrThrow(insured.getInsuredId());

        insuredMapper.updateInsuredEntity(insured, fetchedInsured);
        insuredRepository.save(fetchedInsured);
    }

    @Override
    public void remove(long insuredId) {
        InsuredEntity fetchedEntity = getInsuredOrThrow(insuredId);
        insuredRepository.delete(fetchedEntity);
    }

    @Override
    public InsuredDTO getByInsuranceId(long insuranceId) {
        return null;
    }

    private InsuredEntity getInsuredOrThrow(long insuredId) {
        return insuredRepository
                .findById(insuredId)
                .orElseThrow(InsuredNotFoundException::new);

    }
}


