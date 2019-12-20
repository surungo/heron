public class Main {
	
	public static void main(String... args) {
		if(args!=null && args.length == 0) {
			help();
		}else {
	
			for (String arg : args) {
			    arg = arg.toUpperCase().trim();
	            if (arg.contains("-n")) {
	                System.out.println("Ignorando carros com mais de 10 anos");
	            } 
			}
			
			
		}
		
		
	}
	
	private static void help() {
		System.out.println("Preencha os parametros");
		System.out.println("-s:texto ");
		
	}

	public Main() {
		super();
	}

}