package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import joeymod.cards.ForgottenCard;

public class BlankSlateAction extends AbstractGameAction {
    public void update() {

        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ForgottenCard) {
                AbstractDungeon.player.hand.moveToDeck(c, true);
            }
        }
        for (AbstractCard c : AbstractDungeon.player.discardPile.group) {
            if (c instanceof ForgottenCard) {
                AbstractDungeon.player.discardPile.moveToDeck(c, true);
            }
        }
        this.isDone = true;
    }
}