package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

public class BreakTheCycleAction extends AbstractGameAction {

    private int magicNumber;

    public AbstractPlayer p;

    public BreakTheCycleAction(int magicNumber) {
        System.out.println("FA constructor called...");
        this.magicNumber = magicNumber;
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        System.out.println("FA Update reached...");
        if (ForgetAction.forgottenCards.get(0).type == AbstractCard.CardType.ATTACK) {
            addToTop(new GainBlockAction(p, ForgetAction.forgottenCards.get(0).damage));
        }
        System.out.println("done with update...");
        this.isDone = true;
    }
}