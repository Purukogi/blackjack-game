import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {

    public static void startGame(){

        Scanner scan = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffleDeck();

        Player dealer = new Player(new ArrayList<String>(), 0,  new int[2]);
        Player player = new Player(new ArrayList<String>(), 0,  new int[2]);

        int gameStop = 1;

        deck = dealer.drawsACard(deck);
        deck = dealer.drawsACard(deck);
        dealer.handValuesRange = dealer.computeHandValuesRange();

        deck = player.drawsACard(deck);
        deck = player.drawsACard(deck);
        player.handValuesRange = player.computeHandValuesRange();

        System.out.println("Dealer cards " + dealer.hand.getLast() + " and one unknown.");
        System.out.println("Your cards " + player.hand);

        while(gameStop != 2 && player.handValuesRange[0] <= 21){

            System.out.println("What do you want to do? \n 1 - Draw \n 2 - Stop");
            gameStop = scan.nextInt();
            if(gameStop == 1){
                deck = player.drawsACard(deck);
                player.handValuesRange = player.computeHandValuesRange();
                System.out.println("Your cards " + player.hand);
            }

        }


        if (player.handValuesRange[0] > 21){
            System.out.println("You busted! You lose.");
            return;
        }

        if(player.closestValueTo21() == 21){
            System.out.println("Black Jack! You win!");
            return;
        }

        //dealer's strategy is to draw until he has at least 17
        while(dealer.handValuesRange[0] < 17){
            System.out.println("Dealer draws a card.");
            deck = dealer.drawsACard(deck);
            dealer.handValuesRange = dealer.computeHandValuesRange();
        }

        if(dealer.handValuesRange[0] > 21){
            System.out.println("Dealer busted!" + dealer.hand + "\n You win!");
            return;
        }

        System.out.println("Dealer cards: " + dealer.hand);
        System.out.println("Your cards: " + player.hand);

        if(player.closestValueTo21() > dealer.closestValueTo21()){
            System.out.println("You win!");
        }else{
            System.out.println("You lose!");
        }

        scan.close();

    }


}
