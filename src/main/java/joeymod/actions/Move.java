package joeymod.actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import joeymod.powers.AbstractSleeperPower;
import joeymod.relics.AbstractSleeperRelic;

public class Move {

    public static ForgottenCard toForgottenPile(CardGroup g, AbstractCard c, boolean immediateDiscard) {
        for (AbstractRelic r : AbstractDungeon.player.relics)
            if (r instanceof AbstractSleeperRelic) {
                ((AbstractSleeperRelic) r).onForget(c);
            }
        for (AbstractPower p : AbstractDungeon.player.powers)
            if (p instanceof AbstractSleeperPower) {
                ((AbstractSleeperPower) p).onForget(c);
            }
        g.removeCard(c);
        ForgottenCard newForgottenCard = new ForgottenCard(c);
        g.addToBottom(newForgottenCard);
        ((MySleeperPlayer) AbstractDungeon.player).forgottenPile.addToTop(c);
        System.out.println("Group just prior to moving to discard: " + g.group);
        if (immediateDiscard) {
            g.moveToDiscardPile(newForgottenCard);
        }
        return newForgottenCard;
    }
}