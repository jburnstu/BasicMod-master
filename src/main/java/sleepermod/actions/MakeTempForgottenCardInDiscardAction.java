package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MakeTempForgottenCardInDiscardAction extends AbstractGameAction {
    private AbstractCard cardToAdd;
    private AbstractCard cardToForget = null;
    private AbstractGameAction makeCardAction = null;

    public MakeTempForgottenCardInDiscardAction(AbstractCard cardToAdd, int amount) {
        this.cardToAdd = cardToAdd;
        this.amount = amount;
        this.startDuration = Settings.FAST_MODE ? Settings.ACTION_DUR_FAST : 0.5F;
        this.duration = this.startDuration;
    }

    public void update() {
        if (this.duration == this.startDuration) {
            makeCardAction = new MakeTempCardInDiscardAction(this.cardToAdd, this.amount);
            addToBot(makeCardAction);
            System.out.println("post-make discard pile: " + AbstractDungeon.player.discardPile.toString());
        }
        if (makeCardAction.isDone) {
            System.out.println("isDone true. discard pile: " + AbstractDungeon.player.discardPile.toString());
            CardGroup discard = AbstractDungeon.player.discardPile;
            for (int count = 0; count < this.amount; count++) {
                System.out.println("Loop activated: count = " + count);
                Move.toForgottenPile(discard, discard.getTopCard(), false);
            }
        this.isDone = true;
        }
        this.tickDuration();
    }
}