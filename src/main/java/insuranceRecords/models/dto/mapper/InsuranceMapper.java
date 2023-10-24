package insuranceRecords.models.dto.mapper;

import insuranceRecords.data.entities.InsuranceEntity;
import insuranceRecords.models.dto.InsuranceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {

    InsuranceEntity toEntity(InsuranceDTO source);

    InsuranceDTO toDTO (InsuranceEntity source);

    void updateInsuranceDTO(InsuranceDTO source, @MappingTarget InsuranceDTO target);

    void updateInsuranceEntity(InsuranceDTO source, @MappingTarget InsuranceEntity target);
}
