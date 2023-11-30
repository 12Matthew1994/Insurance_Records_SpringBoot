package insuranceRecords.models.services;

import insuranceRecords.models.dto.InsuredDTO;
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
class InsuredServiceTest {

    @Autowired
    private InsuredService insuredService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void TestCreateInsured() {
        InsuredDTO insuredDTO = new InsuredDTO();
        insuredDTO.setName("Mateo");
        insuredDTO.setSurname("Novakson");
        insuredDTO.setCity("Kostelec");
        insuredService.create(insuredDTO);

        assertNotNull(insuredDTO.getInsuredId());
    }

    @Test
    void TestgetALLInsured() {
        List<InsuredDTO> insuredList = insuredService.getALL();
        assertFalse(insuredList.isEmpty());
    }

    @Test
    void TestgetByIdInsured() {
        Long existingInsuredId = 4L;

        InsuredDTO existingInsured = insuredService.getById(existingInsuredId);
        InsuredDTO fetchedInsured = insuredService.getById(existingInsuredId);

        assertNotNull(fetchedInsured);
        assertEquals(existingInsuredId, fetchedInsured.getInsuredId());

    }

    @Test
    void TestEditInsured() {
        InsuredDTO insuredDTO = insuredService.getALL().get(0);
        insuredDTO.setName("Květoslav");
        insuredService.edit(insuredDTO);
        InsuredDTO updateInsured = insuredService.getById(insuredDTO.getInsuredId());

        assertEquals("Květoslav", updateInsured.getName());

    }

}