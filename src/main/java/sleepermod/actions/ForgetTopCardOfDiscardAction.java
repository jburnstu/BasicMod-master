package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ForgetTopCardOfDiscardAction extends AbstractGameAction {

    public ForgetTopCardOfDiscardAction(int amount) {
        this.amount = amount;
    }

    public void update() {
        CardGroup discard = AbstractDungeon.player.discardPile;
        for (int count = 0; count < this.amount; count++) {
            System.out.println("Loop activated: count = " + count);
            Move.toForgottenPile(discard, discard.getTopCard(), false);
        }
        this.isDone = true;
    }
}