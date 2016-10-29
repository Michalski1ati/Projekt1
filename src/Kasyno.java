
public class Kasyno {

	public static void main(String[] args) {
		Dane dane = new Dane();
                BlackJack blackjack = BlackJack.getInstance();
		Bandyta bandyta = Bandyta.getInstance();
		

		System.out.println("Witaj w kasynie!\nWybierz grę:\nJ - Jednoręki Bandyta\nB - BlackJack\nW - Wyjście");
		String choise = dane.enterString().toUpperCase();

		try {
			if(choise.equals("J")) {
			bandyta.playBandyta();
		} else if(choise.equals("B")) {
			blackjack.playBlackJack();
		} 
		} catch (NullPointerException e) {
			System.out.println("");
		}

	}

}
