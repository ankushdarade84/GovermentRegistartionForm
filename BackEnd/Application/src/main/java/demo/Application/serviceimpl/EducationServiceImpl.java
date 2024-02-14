package demo.Application.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import demo.Application.model.Education;
import demo.Application.model.User;
import demo.Application.pojo.EducationPojo;
import demo.Application.repo.EducationRepo;
import demo.Application.repo.UserRepository;
import demo.Application.service.EducationService;



@Service
public class EducationServiceImpl implements EducationService {
	final static Logger logger = LoggerFactory.getLogger(EducationServiceImpl.class);


	 @Autowired
	    private EducationRepo educationRepository;

	    @Autowired
	    private UserRepository userRepository;

	    @Override
	    public String saveEducation(Long userId, EducationPojo educationPojo) {
	    	logger.info("Education Saving Started");
	    	try {
	            Optional<User> user = userRepository.findById(userId);
	            if (user.isPresent()) {
	                User newUser = user.get();
	                Education newEducation = new Education();
	                BeanUtils.copyProperties(educationPojo, newEducation);
	                newEducation.setUserId(newUser);

	                Education educationSaved = educationRepository.save(newEducation);
	                return "Education saved with ID " + educationSaved.getEduId();
	            } else {
	                throw new RuntimeException("User not found");
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("Error occurred while saving Education details");
	        }
	    }

//	    @Override
//	    public List<EducationPojo> getAllEducations() {
//	    	logger.info("Education Details Getting Started");
//	        List<Education> educationList = educationRepository.findAll();
//	        List<EducationPojo> educationPojoList = new ArrayList<>();
//	        for (Education education : educationList) {
//	            EducationPojo educationPojo = new EducationPojo();
//	            BeanUtils.copyProperties(education, educationPojo);
//	            educationPojo.setUserId(education.getUserId().getId());
//	            educationPojoList.add(educationPojo);
//	        }
//	        return educationPojoList;
//	    }
//	    
	    @Override
	    public List<EducationPojo> getAllEducations() {
	    	logger.info("Education Details Getting Started");
	        try {
	            List<Education> educationList = educationRepository.findAll();
	            List<EducationPojo> educationPojoList = new ArrayList<>();
	            for (Education education : educationList) {
	                EducationPojo educationPojo = new EducationPojo();
	                BeanUtils.copyProperties(education, educationPojo);
	                educationPojo.setUserId(education.getUserId().getId());
	                educationPojoList.add(educationPojo);
	            }
	            return educationPojoList;
	        } catch (Exception e) {
	            logger.error("Error occurred while getting all educations: {}", e.getMessage());
	            throw new RuntimeException("Error occurred while getting all educations");
	        }
	    }

//	    @Override
//	    public EducationPojo getEducationById(Long educationId) {
//	        Optional<Education> educationOptional = educationRepository.findById(educationId);
//	        if (educationOptional.isPresent()) {
//	            Education education = educationOptional.get();
//	            EducationPojo educationPojo = new EducationPojo();
//	            BeanUtils.copyProperties(education, educationPojo);
//	            educationPojo.setUserId(education.getUserId().getId());
//	            return educationPojo;
//	        } else {
//	            throw new RuntimeException("Education not found");
//	        }
//	    }

	    @Override
	    public EducationPojo getEducationById(Long educationId) {
	    	logger.info("Education Details Getting By Id Started");
	        try {
	            Optional<Education> educationOptional = educationRepository.findById(educationId);
	            if (educationOptional.isPresent()) {
	                Education education = educationOptional.get();
	                EducationPojo educationPojo = new EducationPojo();
	                BeanUtils.copyProperties(education, educationPojo);
	                educationPojo.setUserId(education.getUserId().getId());
	                return educationPojo;
	            } else {
	                throw new RuntimeException("Education not found");
	            }
	        } catch (Exception e) {
	            logger.error("Error occurred while getting education by ID {}: {}", educationId, e.getMessage());
	            throw new RuntimeException("Error occurred while getting education by ID");
	        }
	    }	    
	    
	    @Override
	    public List<Education> getEducationsByUserId(Long userId) {
	    	logger.info("Education Details Getting By UserId Started");
	    	try {
				Optional<User> user=userRepository.findById(userId);
				if(user.isPresent()) {
					return educationRepository.findByUserId(user.get());
				}

				}
				catch(Exception e) {
					logger.error("Error while getting ticked based on userid", e.getMessage());
					throw new RuntimeException("Ticket Error");
				}
				return null;
			}
	    
	    
	    
	   

		@Override
		public String deleteEducation(Long eduId) {
			logger.info("Education Details Deleting By Id Started");
			try {
				educationRepository.deleteById(eduId);
				logger.info("User with id {} has been deleted successfully.", eduId);
				return "Education Delete Success  " +eduId;
			} catch (Exception e) {
				logger.error("Error occurred while Delete By Id {}", e.getMessage());
				throw new RuntimeException("Education Error");
			}

		}
	
	    @Override
	    public String updateEducation(Long educationId, EducationPojo updatedEducation) {
	    	logger.info("Education Details Updating By Id Started");
	        try {
	            Optional<Education> existingEducationOptional = educationRepository.findById(educationId);
	            if (existingEducationOptional.isPresent()) {
	                Education existingEducation = existingEducationOptional.get();
	                BeanUtils.copyProperties(updatedEducation, existingEducation, "eduId", "user");
	                // Assuming that "eduId" and "user" are not allowed to be updated
	                Education savedEducation = educationRepository.save(existingEducation);
	                return "Education updated with ID " + savedEducation.getEduId();
	            } else {
	                throw new RuntimeException("Education not found");
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("Error occurred while updating Education details");
	        }
	    }


}
