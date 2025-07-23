package joeymod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;
import joeymod.powers.ObliviousPower;

import static joeymod.JoeyBasicMod.makeID;

public class TeddyBear extends AbstractRelic {
    public static final String ID = "TeddyBear";

    private static final int HEALTH_AMT = 6;

    public TeddyBear() {
        super("TeddyBear", "burningBlood.png", AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.MAGICAL);
    }

    public AbstractRelic makeCopy() {
        return new TeddyBear();
    }

    public boolean usedThisTurn = false;

    public void atTurnStart() {
        usedThisTurn = false;
    }

    public void onCardDraw (AbstractCard drawnCard) {
        if (!usedThisTurn && drawnCard instanceof ForgottenCard) {
            addToBot(new DrawCardAction(1));
        }
    }
}