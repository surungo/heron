package teste;

//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class encoderPassword {

	//@Autowired
	//private static PasswordEncoder passwordEncoder;

    public static void main(String[] args)
    {
    	try {
	    	String password = "6f61e3ab-9ec6-e8f0-24ac-9e59df20e80c";
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String hashedPassword = passwordEncoder.encode(password);

			System.out.println(hashedPassword);
    	}catch (Exception e) {
    		System.out.println(e.getMessage());
		}

    }



}



