import java.util.ArrayList;
import java.util.Scanner;

public class BlackJack {

    public static void startGame(int doubloons){

        Scanner scan = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffleDeck();

        Player dealer = new Player(new ArrayList<String>(), 0,  new int[2], 0);
        Player player = new Player(new ArrayList<String>(), 0,  new int[2], doubloons);
        int betValue;
        int gameStop = 1;

        while (gameStop == 1){

            int handStop = 1;

            System.out.println("Your money: " + player.money);
            System.out.println("Place down your bet!");
            betValue = scan.nextInt();
            player.money -= betValue;

            deck = dealer.drawsACard(deck);
            deck = dealer.drawsACard(deck);
            dealer.handValuesRange = dealer.computeHandValuesRange();

            deck = player.drawsACard(deck);
            deck = player.drawsACard(deck);
            player.handValuesRange = player.computeHandValuesRange();

            System.out.println("Dealer cards " + dealer.hand.getLast() + " and one unknown.");
            System.out.println("Your cards " + player.hand);

            if(player.closestValueTo21() == 21){

                if (dealer.closestValueTo21() == 21){
                    System.out.println("Both you and the dealer got a blackjack! It's a draw.");
                    player.money += betValue;
                }else{
                    System.out.println("Blackjack! You win!");
                    player.money += (5*betValue)/2;
                }
            } else {

                while(handStop != 2 && player.handValuesRange[0] <= 21){

                    System.out.println("What do you want to do? \n 1 - Hit \n 2 - Stand");
                    if(player.hand.size() == 2){
                        System.out.println("3 - Double Down");
                    }

                    handStop = scan.nextInt();
                    if(handStop == 1){
                        deck = player.drawsACard(deck);
                        player.handValuesRange = player.computeHandValuesRange();
                        System.out.println("Your cards " + player.hand);
                    }

                    if(handStop == 3){
                        player.money -= betValue;
                        betValue *= 2;
                        deck = player.drawsACard(deck);
                        player.handValuesRange = player.computeHandValuesRange();
                    }

                }


                if (player.handValuesRange[0] > 21){
                    System.out.println("You busted! You lose.");
                }else {

                    //dealer's strategy is to draw until he has at least 17
                    while(dealer.handValuesRange[0] < 17){
                        System.out.println("Dealer draws a card.");
                        deck = dealer.drawsACard(deck);
                        dealer.handValuesRange = dealer.computeHandValuesRange();
                    }

                    if(dealer.handValuesRange[0] > 21){
                        System.out.println("Dealer busted!" + dealer.hand + "\n You win!");
                        player.money += 2*betValue;
                    }else{
                        System.out.println("Dealer cards: " + dealer.hand);
                        System.out.println("Your cards: " + player.hand);

                        if(player.closestValueTo21() == dealer.closestValueTo21()){
                            System.out.println("It's a draw!");
                            player.money += betValue;
                        }else if(player.closestValueTo21() > dealer.closestValueTo21() ){
                            System.out.println("You win!");
                            player.money += 2*betValue;
                        }else{
                            System.out.println("You lose!");
                        }
                    }

                }

            }


            if(player.money <= 0) {
                System.out.println("You're out of doubloons! Thanks for your patronage!");
                break;
            }else{
                player.handReset();
                dealer.handReset();
                System.out.println("Keep playing? \n 1 - Yes \n 2 - No");
                gameStop = scan.nextInt();
            }

        }

        if (player.money > 0){
            System.out.println("You finished the game with " + player.money + " doubloons.");
            if(player.money > doubloons){
                System.out.println("Nice profits!");
            }else {
                System.out.println("Better luck next time!");
            }
        }

        scan.close();

    }


}
