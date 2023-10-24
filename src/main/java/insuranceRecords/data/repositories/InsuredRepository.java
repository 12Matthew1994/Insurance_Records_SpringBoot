package insuranceRecords.data.repositories;

import insuranceRecords.data.entities.InsuredEntity;
import org.springframework.data.repository.CrudRepository;

public interface InsuredRepository extends CrudRepository<InsuredEntity, Long> {
}
