package macro.others;

import java.io.IOException;

public class OpenApp {

	public static void main(String... args) throws IOException {
		if(args == null)System.out.println("Preencha um parametro: exemplo notepad.exe");
		ProcessBuilder pb = new ProcessBuilder(args);
		pb.start();
	}

}
