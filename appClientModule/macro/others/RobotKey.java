package macro.others;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;

import javax.swing.JTextField;

public class RobotKey {

	private static char charStr;

	public static void main(String... args) throws IOException {

		Robot myRobot;
		
		HashMap<String,Integer> charsInt = new HashMap<String, Integer>();
		charsInt.put("A", KeyEvent.VK_A);
		charsInt.put("B", KeyEvent.VK_B);
		charsInt.put("C", KeyEvent.VK_C);
		charsInt.put("D", KeyEvent.VK_D);
		charsInt.put("E", KeyEvent.VK_E);
		charsInt.put("F", KeyEvent.VK_F);
		charsInt.put("G", KeyEvent.VK_G);
		charsInt.put("H", KeyEvent.VK_H);
		charsInt.put("I", KeyEvent.VK_I);
		charsInt.put("J", KeyEvent.VK_J);
		charsInt.put("K", KeyEvent.VK_K);
		charsInt.put("L", KeyEvent.VK_L);
		charsInt.put("M", KeyEvent.VK_M);
		charsInt.put("N", KeyEvent.VK_N);
		charsInt.put("O", KeyEvent.VK_O);
		charsInt.put("P", KeyEvent.VK_P);
		charsInt.put("Q", KeyEvent.VK_Q);
		charsInt.put("R", KeyEvent.VK_R);
		charsInt.put("S", KeyEvent.VK_S);
		charsInt.put("T", KeyEvent.VK_T);
		charsInt.put("U", KeyEvent.VK_U);
		charsInt.put("V", KeyEvent.VK_V);
		charsInt.put("W", KeyEvent.VK_W);
		charsInt.put("X", KeyEvent.VK_X);
		charsInt.put("Y", KeyEvent.VK_Y);
		charsInt.put("Z", KeyEvent.VK_Z);
		charsInt.put(" ", KeyEvent.VK_SPACE);
		charsInt.put("0", KeyEvent.VK_0);
		charsInt.put("1", KeyEvent.VK_1);
		charsInt.put("2", KeyEvent.VK_2);
		charsInt.put("3", KeyEvent.VK_3);
		charsInt.put("4", KeyEvent.VK_4);
		charsInt.put("5", KeyEvent.VK_5);
		charsInt.put("6", KeyEvent.VK_6);
		charsInt.put("7", KeyEvent.VK_7);
		charsInt.put("8", KeyEvent.VK_8);
		charsInt.put("9", KeyEvent.VK_9);
		charsInt.put("a", KeyEvent.VK_A);
		charsInt.put("b", KeyEvent.VK_B);
		charsInt.put("c", KeyEvent.VK_C);
		charsInt.put("d", KeyEvent.VK_D);
		charsInt.put("e", KeyEvent.VK_E);
		charsInt.put("f", KeyEvent.VK_F);
		charsInt.put("g", KeyEvent.VK_G);
		charsInt.put("h", KeyEvent.VK_H);
		charsInt.put("i", KeyEvent.VK_I);
		charsInt.put("j", KeyEvent.VK_J);
		charsInt.put("k", KeyEvent.VK_K);
		charsInt.put("l", KeyEvent.VK_L);
		charsInt.put("m", KeyEvent.VK_M);
		charsInt.put("n", KeyEvent.VK_N);
		charsInt.put("o", KeyEvent.VK_O);
		charsInt.put("p", KeyEvent.VK_P);
		charsInt.put("q", KeyEvent.VK_Q);
		charsInt.put("r", KeyEvent.VK_R);
		charsInt.put("s", KeyEvent.VK_S);
		charsInt.put("t", KeyEvent.VK_T);
		charsInt.put("u", KeyEvent.VK_U);
		charsInt.put("v", KeyEvent.VK_V);
		charsInt.put("w", KeyEvent.VK_W);
		charsInt.put("x", KeyEvent.VK_X);
		charsInt.put("y", KeyEvent.VK_Y);
		charsInt.put("z", KeyEvent.VK_Z);
		
		charsInt.put("z", KeyEvent.VK_Z);
		
		
		try {
			ProcessBuilder pb = new ProcessBuilder("notepad.exe");
			pb.start();
			myRobot = new Robot();

			String myString = "TESTE TEXTO 360 teste";
			
			for( int i = 0; myString.length() > i; i++ ) {
				myRobot.delay(100);
				String charStr = String.valueOf(myString.charAt(i));
				int char_int =  charsInt.get(charStr);
				System.out.println("char: "+charStr+" - "+char_int+"  upper: "+Character.isUpperCase(charStr.charAt(0))  );
				if(Character.isUpperCase(charStr.charAt(0))) {
					myRobot.keyPress(KeyEvent.VK_SHIFT); 
				}
				myRobot.keyPress(char_int);
				myRobot.keyRelease(char_int);
				if(Character.isUpperCase(charStr.charAt(0))) {
					myRobot.keyRelease(KeyEvent.VK_SHIFT); 
				}
			}

		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
