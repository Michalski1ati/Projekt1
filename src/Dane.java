
import java.util.Scanner;

public class Dane {
	private Scanner input;

	public Dane() {
		input = new Scanner(System.in);}

	public void close() {
		input.close();}

	public int enterInt() {
		int number = input.nextInt();
		input.nextLine();
		return number;}

	public String enterString() {
		String string = input.nextLine();
		return string;}
}
