import java.util.InputMismatchException;
import java.util.Random;

public class Bandyta {
	private static Bandyta instance = null;
	private int a, b, c;
	int rate = 100;
	Gracz gamer;
	Random random = new Random();
	Dane dane = new Dane();

//********** Singleton **********

	public static synchronized Bandyta getInstance() {
		if(instance == null){
			instance = new Bandyta();
		}
		return instance;
	}

//********** Glowna metoda tej klasy sterujaca gra Bandyta **********

	public void playBandyta() {
		String decision = "T";

		enterMoney();
                
		while(decision.equals("T")) {
			if(gamer.money < rate){
				System.out.println("Masz mniej niż 100$. Przegrałeś.");
				return;
			}

			draw();

			System.out.println("Jeśli chcesz zagrać jeszcze raz kliknij \"T\". ");
                        System.out.println("Aby zakończyć kliknij dowolny klawisz.");
			decision = dane.enterString().toUpperCase();
		}
		System.out.println("Koniec");
	}

//********** Klasa wewnetrzna - tworzy gracza i operuje jego pieniedzmi **********

	public class Gracz {
		private int money;

		public void addMoney(int x) {
			money += x;
		}

		public void substractMoney(int x) {
			money -= x;
		}

		public Gracz(int money) {
			this.money = money;
		}
	}

//********** Metoda odpowiadajaca za losowanie **********

	public void draw() {
		a = random.nextInt(7)+1;
		b = random.nextInt(7)+1;
		c = random.nextInt(7)+1;
                
                System.out.println("Rozpoczynamy Losowanie!");
		System.out.println(+a+" "+b+" "+c);

		if(a == b && a == c) {
			System.out.println("Wygrałeś "+rate+" $!");
			gamer.addMoney(rate);

		} else {
			System.out.println("Przegrałeś "+rate+" $!");
			gamer.substractMoney(rate);
		}

		System.out.println("Masz "+gamer.money+" $");
	}

//********** Metoda odpowiadajaca za wprowadzanie pieniedzy przez uzytkownika **********

	public void enterMoney() {
		try {
		System.out.println("Wpisz ilość pieniędzy.\n Koszt jednego zakładu to 100$.");
		int bet = dane.enterInt();
		gamer = new Gracz(bet);
		} catch(InputMismatchException e){
			System.out.println("Błąd");
		}
	}
}
