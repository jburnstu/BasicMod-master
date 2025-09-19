package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class AndAnotherThingAction extends AbstractGameAction {

    private int magicNumber;

    public AbstractPlayer p;

    public AndAnotherThingAction(int magicNumber) {
        this.magicNumber = magicNumber;
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        System.out.println("FA Update reached...");
        if (AwakenAction.awakenedCards.get(0).cost >= this.magicNumber) {
            addToTop(new AwakenAction(1,false));
        }
        System.out.println("done with update...");
        this.isDone = true;
    }
}