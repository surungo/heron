
import pkg.db.ExceptionBusiness;
import pkg.db.UtilityBusiness;

public class resetSenha {

	public static void main(String[] args) {

		String sistema = "corporat";	
		String operador = (args[0]==null)?"054837":args[0];
		String ambiente = (args[1]==null)?"RACESP":args[1];
		UtilityBusiness utilityBusiness = new UtilityBusiness( ambiente, sistema);
		
	try {
		
		System.out.println("---=====  RESET SENHA  =====---");
		System.out.println(utilityBusiness.ambiente());
		System.out.println(utilityBusiness.resetSenha(operador));
	
	
	} catch (ExceptionBusiness e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		
	}
}

