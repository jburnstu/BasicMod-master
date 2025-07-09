package joeymod.actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class Move {

    public static ForgottenCard toForgottenPile(CardGroup g, AbstractCard c, boolean immediateDiscard) {
        g.removeCard(c);
        ForgottenCard newForgottenCard = new ForgottenCard(c);
        g.addToBottom(newForgottenCard);
        ((MySleeperPlayer) AbstractDungeon.player).forgottenPile.addToTop(c);
        if (immediateDiscard) {
            g.moveToDiscardPile(newForgottenCard);
        }
        return newForgottenCard;
    }
}