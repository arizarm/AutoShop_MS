package sg.edu.iss.team1.validator;


import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import sg.edu.iss.team1.model.User;
import sg.edu.iss.team1.service.UserService;



@Component
public class UserValidator implements Validator {

	@Autowired
	UserService uservice;
	@Override
	public boolean supports(Class<?> team) {
		return User.class.isAssignableFrom(team);
	}

	@Override
	public void validate(Object arg0, Errors errors) {
		User user = (User) arg0;

		
boolean isUser=true;
		
		ArrayList<User> us=uservice.findAllUsers();
		
		for (User user2 : us) {
			if (user2.getName()==user.getName()&&user2.getPassword()!=user.getPassword()) {
				isUser=true;
			}		
		}
			
		if (isUser==false) {		
			errors.reject("name");
			errors.rejectValue("name", "errors.user.name","The user does not exist!");
			return;
		}
		
		ValidationUtils.rejectIfEmpty(errors, "name", "error.user.name.empty");
		ValidationUtils.rejectIfEmpty(errors, "password", "error.user.password.empty");
		
	
		System.out.println(user .toString());
	}

}
