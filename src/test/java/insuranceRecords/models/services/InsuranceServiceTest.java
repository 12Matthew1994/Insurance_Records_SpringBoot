package insuranceRecords.models.services;

import insuranceRecords.models.dto.InsuranceDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class InsuranceServiceTest {

    @Autowired
    private InsuranceService insuranceService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testCreateInsurance() {
        InsuranceDTO insuranceDTO = new InsuranceDTO();

        insuranceService.createInsurance(insuranceDTO);

        assertNotNull(insuranceDTO.getInsuranceId());
    }

    @Test
    void testGetAllInsurance() {
        List<InsuranceDTO> insuranceList = insuranceService.getALL();
        assertFalse(insuranceList.isEmpty());
    }

    @Test
    void TestGetByIdInsurance() {
        InsuranceDTO existingInsurance = insuranceService.getById(8);
        Long existingInsuranceId = existingInsurance.getInsuranceId();
        InsuranceDTO fetchedInsurance = insuranceService.getById(existingInsuranceId);

        assertNotNull(fetchedInsurance);
        assertEquals(existingInsuranceId, fetchedInsurance.getInsuranceId());

    }

    @Test
    void TestEditInsurance() {
        InsuranceDTO insuranceDTO = insuranceService.getALL().get(0);
        insuranceDTO.setInsuranceType("Pojištění majetku");
        insuranceService.edit(insuranceDTO);
        InsuranceDTO updateInsurance = insuranceService.getById(insuranceDTO.getInsuranceId());

        assertEquals("Pojištění majetku", updateInsurance.getInsuranceType());
    }
}