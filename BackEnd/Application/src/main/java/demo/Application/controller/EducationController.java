package demo.Application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mobileApp.demo.model.Ticket;

import demo.Application.model.Education;
import demo.Application.pojo.EducationPojo;
import demo.Application.service.EducationService;
import demo.Application.serviceimpl.EducationServiceImpl;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EducationController {
	
	@Autowired
    private EducationService educationService;
	

    @PostMapping("/educationsave/{userId}")
    public ResponseEntity<String> saveEducation(
            @PathVariable Long userId,
            @RequestBody EducationPojo educationPojo) {
        String savedEducation = educationService.saveEducation(userId, educationPojo);
        return ResponseEntity.ok(savedEducation);
    }

    @GetMapping("/getalleducation")
    public ResponseEntity<List<EducationPojo>> getAllEducations() {
        List<EducationPojo> educationList = educationService.getAllEducations();
        return ResponseEntity.ok(educationList);
    }

    @GetMapping("/geteducationbyid/{eduId}")
    public ResponseEntity<EducationPojo> getEducationById(@PathVariable Long eduId) {
        EducationPojo educationDetails = educationService.getEducationById(eduId);
        return ResponseEntity.ok(educationDetails);
    }

//    @GetMapping("/geteducationbyuser/{userId}")
//    public ResponseEntity<List<Education>> getEducationsByUserId(@PathVariable Long userId) {
//        List<Education> educationList = educationService.getEducationsByUserId(userId);
//        return ResponseEntity.ok(educationList);
//    }
    
    @PutMapping("/updateeducation/{eduId}")
    public ResponseEntity<String> updateEducation(
            @PathVariable Long eduId,
            @RequestBody EducationPojo updatedEducation) {
        String result = educationService.updateEducation(eduId, updatedEducation);
        return ResponseEntity.ok(result);
    }
	@GetMapping(value="/geteducationbyuser/{userId}")
	public List<Education> getEducationsByUserId(@PathVariable Long userId){
		return educationService.getEducationsByUserId(userId);
	}


	@DeleteMapping(value = "/deleteeducation/{eduId}")
	public String deleteUserById(@PathVariable Long eduId) {

		return educationService.deleteEducation(eduId);
	}
	



}
