package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class BreakTheCycleAction extends AbstractGameAction {

    public BreakTheCycleAction() {
        System.out.println("FA constructor called...");
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        System.out.println("FA Update reached...");
        for (AbstractCard c : ForgetAction.forgottenCards) {
            if (c.type == AbstractCard.CardType.ATTACK) {
                addToTop(new GainBlockAction(AbstractDungeon.player, ForgetAction.forgottenCards.get(0).damage));
            }
        }
        System.out.println("done with update...");
        this.isDone = true;
    }
}