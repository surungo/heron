package macro.others;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import pkg.db.ExceptionDAO;

public class mainTestMouse {

	/**
	 * @param args
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws ExceptionDAO
	 */

	public static void main2(String[] args) {
		System.out.println("teste1");
		double worfsMoney = 600.90;
		double bloodWinePrice = 200.30;
		double worfsTotal = bloodWinePrice * 3;
		double worfsTotal2 = Math.floor(worfsTotal);

		System.out.println(worfsMoney >= worfsTotal);

		System.out.println(worfsTotal);
		System.out.println(worfsTotal2);
	}

	public static void main(String[] args) {
		System.out.println("Macro");

		int coords[][] = { { 624, 292 }, { 914, 293 }, { 715, 461 }, { 763, 212 }, { 837, 468 }, { 627, 294 } };

		// int coords[][] = { {500,500},{500,600},{600,600},{600,500},{500,500} };

		int xcord = coords[0][0];
		int ycord = coords[0][1];
		int keycode = KeyEvent.VK_6;

		int antigoX = xcord;
		int antigoY = ycord;
		int x = xcord;
		int y = ycord;

		try {

			ProcessBuilder pb = new ProcessBuilder("c:/windows/system32/mspaint.exe");
			pb.start();

			// Cria a instância do objeto
			Robot robot = new Robot();
			// Move o mouse para as coordenads x e y informadas(em pixel)
			robot.mouseMove(xcord, xcord);
			// Aguarda por um segundo
			robot.delay(1000);
			// Simula o clique do mouse(pressiona e solta)

			for (int[] coord : coords) {
				xcord = coord[0];
				ycord = coord[1];
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseMove(xcord, ycord);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				robot.delay(1000);
			}

			int frequencia = 300;
			int amplitude = 300;
			int angulo = 360;

			for (double ang = 500; ang <= angulo + 100; ang += 10) {
				x = (int) (frequencia * ang / 360);
				// tenta mudar aqui
				y = (int) (Math.sin((Math.PI * ang / 180)) * amplitude / 2);
				robot.mouseMove(antigoX, antigoY + amplitude / 2);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseMove(x, y + amplitude / 2);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);

				antigoX = x;
				antigoY = y;
				robot.delay(1000);

			}

			System.out.println("Color " + getHTMLColorString((Color) robot.getPixelColor(xcord, ycord)));

			robot.keyPress(keycode);
			robot.keyRelease(keycode);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	public static String getHTMLColorString(Color color) {
		String red = Integer.toHexString(color.getRed());
		String green = Integer.toHexString(color.getGreen());
		String blue = Integer.toHexString(color.getBlue());

		return "#" + (red.length() == 1 ? "0" + red : red) + (green.length() == 1 ? "0" + green : green)
				+ (blue.length() == 1 ? "0" + blue : blue);
	}

}