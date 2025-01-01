import java.util.ArrayList;


public class Player {

    ArrayList<String> hand;
    int numberOfAces;
    //two values are stored to account for one aces range
    //[min value, max value]
    int[] handValuesRange;
    int money;

    public Player(ArrayList<String> hand, int numberOfAces, int[] handValuesRange, int money){
        this.hand = hand;
        this.handValuesRange = handValuesRange;
        this.numberOfAces = numberOfAces;
        this.money = money;
    }

    public Deck drawsACard(Deck deck){

        Deck drawnDeck = deck;
        String cardDrawn = deck.cards.getFirst();

        this.hand.add(cardDrawn);
        drawnDeck.cards.removeFirst();

        if (Deck.isAce(cardDrawn)){
            this.numberOfAces ++;
        }

        return drawnDeck;
    }

    public int[] computeHandValuesRange(){

        int[] handValuesRange = new int[2];

        for(String card : this.hand){
            if(Deck.isAce(card)){
                handValuesRange[0] += 1;
                handValuesRange[1] += 11;
            }else{
                handValuesRange[0] += Deck.getCardValue(card);
                handValuesRange[1] += Deck.getCardValue(card);
            }
        }

        return handValuesRange;
    }

    //checks the closest value the player can get from 21 with their hand
    public int closestValueTo21(){
        int closestValue = 0;

        for(String card : this.hand){
            if(!Deck.isAce(card)){
                closestValue += Deck.getCardValue(card);
            }
        }

        for(int i = 1; i <= this.numberOfAces; i++){
            if(closestValue + 11 > 21){
                closestValue += 1;
            }else{
                closestValue += 11;
            }
        }

        return closestValue;
    }

    public void handReset(){
        this.hand = new ArrayList<String>();
    }


}
