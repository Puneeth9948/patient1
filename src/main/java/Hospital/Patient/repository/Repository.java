package Hospital.Patient.repository;

import Hospital.Patient.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface Repository extends JpaRepository<Patient, Long> {

}
