package Hospital.Patient.controller;

import Hospital.Patient.entity.Patient;
import Hospital.Patient.repository.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "*")
public class Controller {
    @Autowired
    private Repository repository;

    @GetMapping("/all")
    public List<Patient> getAll() { return repository.findAll(); }
    @GetMapping("/{id}") public ResponseEntity<Patient> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    public Patient create(@RequestBody Patient patient) { return repository.save(patient); }
    @PutMapping("/{id}")
    public ResponseEntity<Patient> update(@PathVariable Long id, @RequestBody Patient patient) {
        return repository.findById(id).map(p -> {
            p.setName(patient.getName());
            p.setEmail(patient.getEmail());
            p.setPhone(patient.getPhone());

            return ResponseEntity.ok(repository.save(p));
        }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) { repository.deleteById(id); }
}
