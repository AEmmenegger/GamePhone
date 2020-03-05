
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Controller {

	public static void main(String[] args) throws Exception {
		try (var listener = new ServerSocket(59898)) {
			System.out.println("Epic Controllerservice running, du kek");
			Executor pool = Executors.newFixedThreadPool(20); // 20 devices
			while (true) { // always listening for new devices
				pool.execute(new Capitalizer(listener.accept()));
			}
		}
	}

	private static class Capitalizer implements Runnable {
		Robot myRobot;
		private Socket socket;

		Capitalizer(Socket socket) {
			this.socket = socket;
			try {
				myRobot = new Robot();
			} catch (AWTException e) {
				//// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			System.out.println("Connected: " + socket);
			try {
				var in = new Scanner(socket.getInputStream());
				var out = new PrintWriter(socket.getOutputStream(), true);
				while (in.hasNextLine()) {
					// out.println(in.nextLine().toUpperCase());
					String got = in.nextLine();
					//System.out.println(got);
					keyHandler(got);
					// System.out.println(in.nextLine());
				}
			} catch (Exception e) {
				System.out.println("Error:" + socket);
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
				}
				System.out.println("Closed: " + socket);
			}
		}

		private void keyHandler(String toType) {
			boolean pressed;
			String[] parts = toType.split(" ");
			if(parts[2].contentEquals("T")) {
				pressed = true;
			}else {
				pressed = false;
			}
			//pressed = Boolean.valueOf(parts[2]);

			if (parts[0].equals("1")) {
				switch (parts[1]) {
				case "A":
					roboting(KeyEvent.VK_A, pressed);
					break;
				case "B":
					roboting(KeyEvent.VK_B, pressed);
					break;
				case "L":
					roboting(KeyEvent.VK_L, pressed);
					break;
				case "R":
					roboting(KeyEvent.VK_R, pressed);
					break;
				case "U":
					roboting(KeyEvent.VK_U, pressed);
					break;
				case "D":
					roboting(KeyEvent.VK_D, pressed);
					break;
				case "Z":
					roboting(KeyEvent.VK_Z, pressed);
					break;
				}
			}
			else if(parts[0].equals("2")) {
				switch (parts[1]) {
				case "A":
					roboting(KeyEvent.VK_S, pressed);
					break;
				case "B":
					roboting(KeyEvent.VK_F, pressed);
					break;
				case "L":
					roboting(KeyEvent.VK_G, pressed);
					break;
				case "R":
					roboting(KeyEvent.VK_H, pressed);
					break;
				case "U":
					roboting(KeyEvent.VK_J, pressed);
					break;
				case "D":
					roboting(KeyEvent.VK_K, pressed);
					break;
				case "Z":
					roboting(KeyEvent.VK_O, pressed);
					break;
				}
			}
			else if(parts[0].equals("3")) {
				switch (parts[1]) {
				case "A":
					roboting(KeyEvent.VK_0, pressed);
					break;
				case "B":
					roboting(KeyEvent.VK_9, pressed);
					break;
				case "L":
					roboting(KeyEvent.VK_8, pressed);
					break;
				case "R":
					roboting(KeyEvent.VK_7, pressed);
					break;
				case "U":
					roboting(KeyEvent.VK_6, pressed);
					break;
				case "D":
					roboting(KeyEvent.VK_5, pressed);
					break;
				case "Z":
					roboting(KeyEvent.VK_4, pressed);
					break;
				}
			}
			else if(parts[0].equals("4")) {
				switch (parts[1]) {
				case "A":
					roboting(KeyEvent.VK_1, pressed);
					break;
				case "B":
					roboting(KeyEvent.VK_2, pressed);
					break;
				case "L":
					roboting(KeyEvent.VK_3, pressed);
					break;
				case "R":
					roboting(KeyEvent.VK_Y, pressed);
					break;
				case "U":
					roboting(KeyEvent.VK_X, pressed);
					break;
				case "D":
					roboting(KeyEvent.VK_C, pressed);
					break;
				case "Z":
					roboting(KeyEvent.VK_V, pressed);
					break;
				}
			}

		}

		private void roboting(int key, boolean pressed) {
			if (pressed) {
				myRobot.keyPress(key);
			} else {
				myRobot.keyRelease(key);
			}
		}

	}

}
