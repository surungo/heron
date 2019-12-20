package teste;

import java.util.HashMap;

import pkg.db.ExceptionBusiness;
import pkg.db.UtilityBusiness;

public class oracleRun {

	public static void main(String[] args) {

		String sistema = "corporat";	
		String ambiente = "rachmg";	
		//UtilityBusiness utilityBusiness = new UtilityBusiness( ambiente, sistema);
		
		HashMap<String, UtilityBusiness> ubs = new HashMap<String, UtilityBusiness>();
		ubs.put("racdes", new UtilityBusiness( "racdes", sistema));
		ubs.put("ractst", new UtilityBusiness( "ractst", sistema));
		ubs.put("racesp", new UtilityBusiness( "racesp", sistema));
		ubs.put("rachmg", new UtilityBusiness( "rachmg", sistema));
		//ubs.put("racprod", new UtilityBusiness( "racprod", "senharede",""));
		
	try {
		
		for (HashMap.Entry me : ubs.entrySet()) {
			String key = (String) me.getKey();
			UtilityBusiness ub = (UtilityBusiness) me.getValue();
			System.out.println(key+".ambiente()");
			System.out.println(ub.ambiente());
			System.out.println(key+".v_instance()");
			System.out.println(ub.v_instance());
			System.out.println(key+".oracleSYSDATE()");
			System.out.println(ub.oracleSYSDATE());
			System.out.println(key+".oracleDVMatricula(\"00109220\")");
			System.out.println(ub.oracleDVMatricula("00109220"));
		   	
		}
		
		
	} catch (ExceptionBusiness e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}
}

