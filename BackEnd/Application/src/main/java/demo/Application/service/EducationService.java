package demo.Application.service;

import java.util.List;

import demo.Application.model.Education;
import demo.Application.pojo.EducationPojo;

public interface EducationService {
	String saveEducation(Long userId, EducationPojo educationPojo);

	List<EducationPojo> getAllEducations();

	EducationPojo getEducationById(Long eduId);

	List<Education> getEducationsByUserId(Long userId);

	String deleteEducation(Long eduId);
	String updateEducation(Long eduId, EducationPojo updatedEducation);
}
