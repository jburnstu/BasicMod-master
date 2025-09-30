package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;

public class InOneEarAction extends AbstractGameAction {

    public InOneEarAction(){
        System.out.println("DS constructor called...");
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        System.out.println("DS Update reached...");
        for (AbstractCard c : ForgetAction.forgottenCards) {
            addToTop(new DrawCardAction(1));
        }
        this.isDone = true;
    }
}
