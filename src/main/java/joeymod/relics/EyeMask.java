package joeymod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import joeymod.actions.Move;
import joeymod.cards.ForgottenCard;
import joeymod.patches.AbstractCardBackForgottenCardPatch;

import java.util.ArrayList;

import static joeymod.JoeyBasicMod.makeID;


// Forgotten cards remain forgotten in between combats.
public class EyeMask extends AbstractSleeperRelic {
    public static final String ID = makeID(EyeMask.class.getSimpleName());

    public EyeMask() {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL);
        System.out.println("TeddyBear constructor called....");
    }

    public String getUpdatedDescription() {
        return "Try this";
    }


    public AbstractRelic makeCopy() {
        return new EyeMask();
    }

    public ArrayList<AbstractCard> cardsToRemainForgotten;


    @Override
    public void atBattleStartPreDraw () {
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (cardsToRemainForgotten.contains(c)) {
                Move.toForgottenPile(AbstractDungeon.player.drawPile,c,false);
            }
        }
        cardsToRemainForgotten.clear();
    }

    @Override
    public void onVictory () {
        flash();
        for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
            if (!(AbstractCardBackForgottenCardPatch.backForgottenCard.get(c) == null)) {
                cardsToRemainForgotten.add(c);
            }
        }
    }
}