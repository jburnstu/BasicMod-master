package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.cards.ForgottenCard;

public class ReinterpretAction extends AbstractGameAction {

    private int amount;

    public AbstractPlayer p;

    public ReinterpretAction(int amount) {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
        this.p = AbstractDungeon.player;
        this.amount = amount;
    }


    public void update () {
        System.out.println("RA update reached");

        for (int i=0; i < amount; i++) {
            AbstractCard cardToMove = null;
            System.out.println("RA null set -- discard pile: " + this.p.discardPile.group.toString());

            for (AbstractCard c : this.p.discardPile.group) {
                System.out.println("c: " + c.toString());
                if (c instanceof ForgottenCard) {
                    System.out.println("Forgotten card found");
                    cardToMove = c;
                    break;
                }
            }
            if (cardToMove != null) {
                this.p.hand.addToHand(cardToMove);
                this.p.discardPile.removeCard(cardToMove);
            }
        }
        this.isDone = true;
    }
}
