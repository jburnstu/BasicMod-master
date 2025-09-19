package sleepermod.actions;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.cards.ForgottenCard;
import sleepermod.character.MySleeperPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.patches.AbstractCardBackForgottenCardPatch;
import sleepermod.powers.AbstractSleeperPower;
import sleepermod.relics.AbstractSleeperRelic;

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
        for (AbstractMonster mo: AbstractDungeon.getCurrRoom().monsters.monsters) {
            for (AbstractPower p : mo.powers) {
                if (p instanceof AbstractSleeperPower) {
                    ((AbstractSleeperPower) p).onForget(c);
                }
            }
        }
        g.removeCard(c);
        ForgottenCard newForgottenCard = new ForgottenCard(c);
        g.addToBottom(newForgottenCard);
        ((MySleeperPlayer) AbstractDungeon.player).forgottenPile.addToTop(c);
        ((MySleeperPlayer) AbstractDungeon.player).cardsForgottenThisTurn.add(c);
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
            for (AbstractCard otherCard : p.forgottenPile.group) {
                if (otherCard instanceof AbstractSleeperCard) {
                    ((AbstractSleeperCard) otherCard).triggerOnCardAwoken();
                }
            }
            for (AbstractRelic relic : p.relics) {
                if (relic instanceof AbstractSleeperRelic) {
                    ((AbstractSleeperRelic) relic).onAwaken(c);
                }
            }
            for (AbstractPower power : p.powers) {
                if (power instanceof AbstractSleeperPower) {
                    ((AbstractSleeperPower) power).onAwaken(c);
                }
            }
        }
    }
}
