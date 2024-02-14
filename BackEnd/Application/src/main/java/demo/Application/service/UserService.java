package demo.Application.service;

import org.json.JSONObject;

import demo.Application.model.User;
import demo.Application.pojo.UserPojo;



public interface UserService {
	public JSONObject SignUP(User a);

	public JSONObject Login(UserPojo loginUser);

	public JSONObject ValidateEmail(String email);

	public String ResetPassword(UserPojo user);

	public User GetDetailsById(Long id);

	public Object getUserByType(String string);

	public String deleteUserById(Long id);
	
	String updateUser(Long userId, User updatedUser);

}
