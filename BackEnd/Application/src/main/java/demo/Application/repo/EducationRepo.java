package demo.Application.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.Application.model.Education;
import demo.Application.model.User;



public interface EducationRepo extends JpaRepository<Education, Long> {
	
	List<Education> findByUserId(User userId);
//	List<Education> findByUserId(Long user);



}
