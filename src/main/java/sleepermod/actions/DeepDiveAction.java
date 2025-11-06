package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.cards.ForgottenCard;

public class DeepDiveAction extends AbstractGameAction {

    private int magicNumber;

    public AbstractPlayer p;

    public DeepDiveAction() {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.p = AbstractDungeon.player;
    }


    public void update () {
        AbstractCard cardToMove = null;
        for (AbstractCard c : this.p.discardPile.group) {
            System.out.println("c: " + c.toString());
            if (c instanceof ForgottenCard) {
                cardToMove = c;
                break;
            }
        }
        if (cardToMove != null) {
            this.p.discardPile.removeCard(cardToMove);
            this.p.hand.moveToDeck(cardToMove, false);
        }
        this.isDone = true;
    }
}
