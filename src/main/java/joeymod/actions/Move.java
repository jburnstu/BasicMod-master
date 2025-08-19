package joeymod.actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import joeymod.cards.AbstractSleeperCard;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import joeymod.patches.AbstractCardBackForgottenCardPatch;
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
        if (immediateDiscard) {
            if (AbstractDungeon.player.hasPower("Hibernation")) {
                g.moveToDeck(newForgottenCard,true);
            }
            g.moveToDiscardPile(newForgottenCard);
        }
        return newForgottenCard;
    }

    public static void fromForgottenPile(AbstractCard c) {
        MySleeperPlayer p = (MySleeperPlayer) AbstractDungeon.player;
        if (p.forgottenPile.group.contains(c)) {
            ForgottenCard backForgottenCard = AbstractCardBackForgottenCardPatch.backForgottenCard.get(c);
            if (p.drawPile.group.contains(backForgottenCard)) {
                p.drawPile.removeCard(backForgottenCard);
            } else if (p.discardPile.group.contains(backForgottenCard)) {
                p.discardPile.removeCard(backForgottenCard);
            } else if (p.hand.group.contains(backForgottenCard)) {
                p.hand.removeCard(backForgottenCard);}
            p.forgottenPile.removeCard(c);
            p.hand.addToHand(c);
        }
    }
}
