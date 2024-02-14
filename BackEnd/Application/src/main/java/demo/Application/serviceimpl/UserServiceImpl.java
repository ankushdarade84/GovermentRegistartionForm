package demo.Application.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.Application.model.User;
import demo.Application.pojo.UserPojo;
import demo.Application.repo.UserRepository;
import demo.Application.service.UserService;
import demo.Application.util.Utilities;


@Service
public class UserServiceImpl implements UserService {
	final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepo;

	@Override
	public JSONObject SignUP(User a) {
		logger.info("SignUP method started");
		a.setEmail(a.getEmail().toLowerCase());
		JSONObject response = new JSONObject();



		if (a.getType() == null)
			a.setType("user");
		String password = Utilities.hashPassword(a.getPassword());
        a.setPassword(password);

		try {
			userRepo.save(a).toString();
			response.put("Status", true);
			response.put("Message", "SignUp Successful");
			logger.info("SignUP method completed successfully");
			return response;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("SignUP method failed with exception: {}", e.getMessage());
			response.put("Status", false);
			response.put("Message", "SignUp Error");
			throw new RuntimeException("User Error");
		}

	}

	@Override
	public JSONObject Login(UserPojo loginUser) {
		logger.info("Login method started");
		JSONObject response=new JSONObject();
		

		List<User> userList = userRepo.findByEmail(loginUser.getEmail().toLowerCase());
		try {
			if (userList.isEmpty()) {
				logger.warn("Invalid Credentials");
				response.put("Message","Invalid Credentials");
				return response;
			}

			String password = Utilities.hashPassword(loginUser.getPassword());
			loginUser.setPassword(password);

			if (userList.get(0).getPassword().equals(loginUser.getPassword())) {
//			 response.put("Status", true);
//				response.put("Message", "Login Successful");
				logger.info("Login Successful with mail {} : {}", loginUser.getEmail(), loginUser.getPassword());
				response.put("Message","Login Successful");
				response.put("UserType",userList.get(0).getType());
				response.put("LoginId", userList.get(0).getId());
				return response;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("An error occurred while logging in: {}", e.getMessage());
			throw new RuntimeException("User Error");

		}
		logger.warn("Incorrect Password");
		response.put("Message","Incorrect Password");
		return response;

	}

	@Override
	public JSONObject ValidateEmail(String email) {
		logger.info("ValidateEmail method started");
		List<User> userList = userRepo.findByEmail(email.toLowerCase());
		JSONObject response = new JSONObject();
		if (userList.isEmpty()) {
			logger.info("Email registration successful :{} ", email);
			response.put("Status", true);
			response.put("Message","Email registration successful");
			return response;
		}
		logger.warn("Email Already present", email);
		response.put("Status", false);
		response.put("Message","Failed! Email Already Present");
		return response;
	}

	@Override
	public String ResetPassword(UserPojo user) {
		 logger.info("ResetPassword method started");
		List<User> userList = userRepo.findByEmail(user.getEmail().toLowerCase());

		try {
			if (userList.isEmpty()) {
//				 response.put("Status", false);
//				response.put("Message", "Invalid Credentials")
//					return response;
				logger.warn("ResetPassword failed: user with email {} not found", user.getEmail());
				return "Invalid Credentials";
			}

			String password = Utilities.hashPassword(user.getPassword());
			user.setPassword(password);

			String ConfirmPassword = Utilities.hashPassword(user.getConfirmPassword());
			user.setConfirmPassword(ConfirmPassword);

			if (password.equals(ConfirmPassword)) {
				User newUser = userList.get(0);
				newUser.setPassword(user.getPassword());
				userRepo.save(newUser);
				logger.info("Password reset successful for user with email {}", user.getEmail());
				return "Password Reset Successful";

			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("ResetPassword failed with error: {}", e.getMessage());
			throw new RuntimeException("User Error");

		}
		logger.warn("Password reset failed for user with email {} password not match", user.getEmail());
		return "Password Do not match";
	}

	@Override
	public User GetDetailsById(Long id) {
		logger.info("GetDetailsById method started with id: {}", id);

		try {
			User newUser = userRepo.findById(id).orElse(null);
			if (newUser == null) {
				logger.warn("User not found with id: {}", id);
			} else {
				logger.info("User found with id: {}", id);
			}
			return newUser;

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Get details by Id failed {}", e.getMessage());
			throw new RuntimeException("User Error");
		}

	}


	@Override
	public List<User> getUserByType(String type) {
		try {
			logger.info("getUserByType method started");
			List<User> users = userRepo.findByType(type);
			logger.info("getUserByType method finished");
			return users;
		} catch (Exception e) {
			logger.error("Error occurred while By get By Type {}", e.getMessage());
			throw new RuntimeException("User Error");
		}

	}

	@Override
	public String deleteUserById(Long id) {
		try {
			userRepo.deleteById(id);
			logger.info("User with id {} has been deleted successfully.", id);
			return "Delete Success";
		} catch (Exception e) {
			logger.error("Error occurred while Delete By Id {}", e.getMessage());
			throw new RuntimeException("User Error");
		}

	}

	  @Override
	    public String updateUser(Long userId, User updatedUser) {
	        try {
	            Optional<User> existingUserOptional = userRepo.findById(userId);
	            if (existingUserOptional.isPresent()) {
	                User existingUser = existingUserOptional.get();
	                String password = Utilities.hashPassword(updatedUser.getPassword());
	                updatedUser.setPassword(password);
	                BeanUtils.copyProperties(updatedUser, existingUser, "id");
	                // Assuming that "id" is not allowed to be updated
	                User savedUser = userRepo.save(existingUser);
	                return "User updated with ID " + savedUser.getId();
	            } else {
	                throw new RuntimeException("User not found");
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("Error occurred while updating User details");
	        }
	    }

}
