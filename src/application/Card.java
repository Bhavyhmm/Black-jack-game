package application;

public class Card {
	int rank;  // 1 = Ace, 2 = 2, ...., 11 = Jack, 12 = Queen, 13 = King
	String suit; // "Hearts", "Diamonds", "Clubs", "Spades"
	
	public Card(int r, String s){
		rank = r;
		suit = s;
	}

	public String GetFileName() {
		return rank + "_of_" + suit + ".png";
	}
        public int getvalue(){
           return rank;
}
}
