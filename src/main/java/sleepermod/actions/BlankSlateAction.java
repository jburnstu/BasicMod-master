package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.cards.ForgottenCard;

import java.util.ArrayList;

public class BlankSlateAction extends AbstractGameAction {

    public ArrayList<AbstractCard> handCardsToMove = new ArrayList<>();
    public ArrayList<AbstractCard> discardPileCardsToMove = new ArrayList<>();
    public AbstractPlayer p = AbstractDungeon.player;

    public void update() {

        for (AbstractCard c : p.hand.group) {
            if (c instanceof ForgottenCard) {
                handCardsToMove.add(c);
            }
        }
        for (AbstractCard c : p.discardPile.group) {
            if (c instanceof ForgottenCard) {
                discardPileCardsToMove.add(c);
            }
        }
        for (AbstractCard c : handCardsToMove) {
            p.hand.moveToDeck(c, true);
        }
        for (AbstractCard c : discardPileCardsToMove) {
            p.discardPile.moveToDeck(c, true);
        }


        this.isDone = true;
    }
}