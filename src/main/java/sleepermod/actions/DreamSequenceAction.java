package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class DreamSequenceAction extends AbstractGameAction {

    public DreamSequenceAction() {
        System.out.println("FA constructor called...");
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        System.out.println("FA Update reached...");
        for (AbstractCard c : ForgetAction.forgottenCards) {
            addToTop(new DrawCardAction(1));
        }
        System.out.println("done with update...");
        this.isDone = true;
    }
}