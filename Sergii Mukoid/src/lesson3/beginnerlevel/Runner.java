package lesson3.beginnerlevel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

//Create runner class for each difficulty level in Lesson 3. 
//Upon running the method should ask which task to display and then show the actual task and your solution.

//Tasks are called through Switch.
//The tasks' descriptions are read from .txt file. (grep java)

public class Runner {
	String descriptionPath = "C://Users//Пользователь//git//SM_TestAutomationLessons//Sergii Mukoid//src//lesson3//beginnerlevel//TaskDescription";
	String writePath = "C://Users//SERG//Desktop//Tasks.xls";

	public void allBeginnerTaskDisplay() {
		try (BufferedReader br = new BufferedReader(new FileReader(descriptionPath))) {

			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void beginnerTaskWriteByNumber(int number) throws BiffException, WriteException {
		try (BufferedReader br = new BufferedReader(new FileReader(descriptionPath))) {

			String sCurrentLine;
			JExcelAPI writeFile = new JExcelAPI();

			while ((sCurrentLine = br.readLine()) != null) {
				if (Integer.toString(number).equals(sCurrentLine.substring(0, Integer.toString(number).length())))
					writeFile.writeToExcel(writePath, "Task", 3, 3, sCurrentLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void beginnerTaskRun() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException  {
		try {
			Scanner reader = new Scanner(System.in);
			System.out.println("Which task do you want to run? ");
			int number = reader.nextInt();
			System.out.println("Task description: ");
			try (BufferedReader br = new BufferedReader(new FileReader(descriptionPath))) {
				String sCurrentLine;
				while ((sCurrentLine = br.readLine()) != null) {
					if ((Integer.toString(number)+".").equals(sCurrentLine.substring(0, Integer.toString(number).length()+1)))
						System.out.println(sCurrentLine);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Running the chosen task... ");
			String taskName = "lesson3.beginnerlevel.Task" + number;
			Class.forName(taskName).getMethod("main", String[].class).invoke(null, (Object) null);
		reader.close();
		} catch (ClassNotFoundException err) {
			System.out.println("No task found");
		}
	}

	public static void main(String[] args) throws BiffException, WriteException, ClassNotFoundException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException,
			NoSuchMethodException, SecurityException {
		Runner myInstance = new Runner();
		myInstance.beginnerTaskRun();
	}
}
