import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Deck {

    ArrayList<String> cards;

    public Deck(){
        this.cards = new ArrayList<>(Arrays.asList("As", "2s", "3s", "4s", "5s", "6s", "7s", "8s", "9s", "10s", "Js", "Qs", "Ks", "Ah", "2h", "3h", "4h", "5h", "6h", "7h", "8h", "9h", "10h", "Jh", "Qh", "Kh", "Ac", "2c", "3c", "4c", "5c", "6c", "7c", "8c", "9c", "10c", "Jc", "Qc", "Kc", "Ad", "2d", "3d", "4d", "5d", "6d", "7d", "8d", "9d", "10d", "Jd", "Qd", "Kd"));
    }

    public void shuffleDeck(){

        ArrayList<String> shuffledCards = new ArrayList<>();
        ArrayList<String> tempCards = this.cards;

        Random rand = new Random();
        int randomIndex;

        for (int i = 0; i < 52; i++){
            randomIndex = rand.nextInt(tempCards.size());
            shuffledCards.add(tempCards.get(randomIndex));
            tempCards.remove(randomIndex);
        }

        this.cards = shuffledCards;

    }

    public static boolean isAce(String card){
        return (card.equals("As") || card.equals("Ah") || card.equals("Ac") || card.equals("Ad"));
    }

    public static int getCardValue(String card) {
        switch (card) {
            case "2s", "2h", "2c", "2d" -> {
                return 2;
            }
            case "3s", "3h", "3c", "3d" -> {
                return 3;
            }
            case "4s", "4h", "4c", "4d" -> {
                return 4;
            }
            case "5s", "5h", "5c", "5d" -> {
                return 5;
            }
            case "6s", "6h", "6c", "6d" -> {
                return 6;
            }
            case "7s", "7h", "7c", "7d" -> {
                return 7;
            }
            case "8s", "8h", "8c", "8d" -> {
                return 8;
            }
            case "9s", "9h", "9c", "9d" -> {
                return 9;
            }
            case "10s", "10h", "10c", "10d", "Js", "Qs", "Ks", "Jc", "Qc", "Kc", "Jh", "Qh", "Kh", "Jd", "Qd", "Kd" -> {
                return 10;
            }
            default -> {
                System.out.println("this is not a valid card name or this is an ace which are treated differently");
            }
        }
        return 0;
    }


}