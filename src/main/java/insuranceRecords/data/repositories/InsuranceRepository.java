package insuranceRecords.data.repositories;

import insuranceRecords.data.entities.InsuranceEntity;
import org.springframework.data.repository.CrudRepository;

public interface InsuranceRepository extends CrudRepository<InsuranceEntity, Long> {


}
