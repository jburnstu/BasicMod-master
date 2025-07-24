package joeymod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;
import joeymod.powers.ObliviousPower;

import static joeymod.JoeyBasicMod.makeID;

public class TeddyBear extends AbstractSleeperRelic {
    public static final String ID = makeID("TeddyBear");

    public TeddyBear() {
        super(makeID("TeddyBear"), AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.MAGICAL);
    }

    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + '\003' + this.DESCRIPTIONS[1];
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
            flash();
            addToBot(new DrawCardAction(1));
        }
    }
}