import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Random;


public class BlackJack {
	private static BlackJack instance = null;
	Gracz gracz;
	int rate = 150;
	String decision;
	Integer gamerKey;
	Integer cardKey;
	int gamerSum;
	int croupierSum;

	Dane dane = new Dane();
	Random random = new Random();
	HashMap<Integer, Integer> cards = new HashMap<>();
	HashMap<Integer, Integer> gamerCards = new HashMap<>();



	public static synchronized BlackJack getInstance() {
		if(instance == null) {
			instance = new BlackJack();
		}
		return instance;
	}



	public void playBlackJack() {
		decision = "T";

		enterMoney();

		while(decision.equals("T")) {
			if(gracz.money < rate){
	System.out.println("Masz mniej niż 150$! Przegrywasz!");
				return;
			}

			clearCards();
			settingCards();
			game();

	System.out.println("Jeśli chcesz zagrać jeszcze raz klknij \"T\". Aby zakończyć kliknij dowolny przycisk...");
			decision = dane.enterString().toUpperCase();
		}
		System.out.println("See ya");
	}



	public void clearCards() {
		gamerKey = 1;
		cardKey = 0;
		gamerSum = 0;
		croupierSum = 0;
		cards.clear();
		gamerCards.clear();

	}



	public void settingCards(){
		int key = 1;
		for(int i=0; i<4; i++){
			for(int j=2; j<12; j++){
				cards.put(key, j);
				key++;
				if(j==10) {
					for(int k=0; k<3; k++){
						cards.put(key,j);
						key++;
					}
				}
			}
		}
	}



	public void game() {
		System.out.println("Twoje karty: ");

		for(int i=0; i<2; i++){
			drawCards(1);
		}

		drawCards(0);
		if(gamerSum > 21) {
			result();
		}
		decision();
	}



public void decision() {
    int choise=0;
	do {
	System.out.println("\nHit-H, Double-D, Stand-S,");
	decision = dane.enterString().toUpperCase();
	if(decision.equals("H")) {
            do {
            System.out.println("Nowe karty:");
            drawCards(1);
            for(Integer value: gamerCards.values()) {
            System.out.print(value+" ");}
		if(gamerSum > 21) {result();
                                    return;}
		choise=1;
		System.out.println("\nHit-H, Double-D, Stand-S,");
		decision = dane.enterString().toUpperCase();
		if(decision.equals("D")) {
			System.out.println("Nowe karty:");
			drawCards(1);
			drawCards(0);
			result();
			choise=1;}
				else if(decision.equals("S")) {
                                    drawCards(0);
                                    if(croupierSum <= 21) {
			if(croupierSum > gamerSum && croupierSum <= 21) {result();}
				else {
					drawCards(0);
					while(croupierSum <= gamerSum && croupierSum < 21)
					drawCards(0);
					result();}
                                                            }
                                  choise=1;
					}
					else if(decision.equals("T")) {
						System.out.println("");
					}
			else {System.out.println("\n");}
				} while(decision.equals("H"));}
			else if(decision.equals("D")) {
				rate = 2*rate;
				System.out.println("Nowa karta");
				drawCards(1);
				drawCards(0);
				result();
				rate = 150;
				choise=1;
			}
			else if(decision.equals("S")) {
				drawCards(0);
				if(croupierSum <= 21) {
	if(croupierSum > gamerSum && croupierSum <= 21) {
						result();}
					else { drawCards(0);
						while(croupierSum <= gamerSum && croupierSum < 21)
						drawCards(0);
						result();}
				}
				choise=1;
			} else {
				System.out.println("Zła litera. Spróbuj jeszcze raz.");
			}
		} while(choise==0);
	}



		public void drawCards(int x) {
			if(x == 1) {
				cardKey = random.nextInt(52)+1;
				while(cards.get(cardKey) != null) {
				System.out.println(cards.get(cardKey));
				gamerCards.put(gamerKey, cards.get(cardKey));
				gamerKey++;
				gamerSum += (int)cards.get(cardKey);
				cards.remove(cardKey);
				}
			} else {
				cardKey = random.nextInt(52)+1;
				while(cards.get(cardKey) != null) {
				System.out.println("Karty krupiera: ");
				System.out.println(cards.get(cardKey));
				croupierSum += (int)cards.get(cardKey);
				cards.remove(cardKey);
				}
			}
		}



		public void result() {
			if(gamerSum > 21) {
				System.out.println("Przegrałeś "+rate+" $!");
				gracz.substractMoney(rate);
			} else if(croupierSum > 21) {
				System.out.println("Wygrałeś "+rate+" $!");
				gracz.addMoney(rate);
			} else if(gamerSum > croupierSum && gamerSum <= 21) {
				System.out.println("Wygrałeś "+rate+" $!");
				gracz.addMoney(rate);
			} else if(gamerSum == croupierSum && gamerSum <= 21) {
				System.out.println("Remis, wygrywasz połowę "+(rate/2)+" $!");
				gracz.addMoney(rate);
			} else {
				System.out.println("Przegrałeś "+rate+" $!");
				gracz.substractMoney(rate);
			}
			System.out.println("Masz "+gracz.money+" $");
		}



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



	public void enterMoney() {
		try {
		System.out.println("Wpisz ile pieniędzy inwestujesz.\n Koszt jednego zakładu to "+rate);
		int bet = dane.enterInt();
		gracz = new Gracz(bet);
		} catch(InputMismatchException e){
			System.out.println("Błąd");
		}
	}
}
