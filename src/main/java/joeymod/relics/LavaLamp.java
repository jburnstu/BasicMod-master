package joeymod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;

public class LavaLamp extends AbstractSleeperRelic {
    public static final String ID = makeID(LavaLamp.class.getSimpleName());

    public LavaLamp() {
        super(ID, AbstractRelic.RelicTier.STARTER, AbstractRelic.LandingSound.MAGICAL);
    }

    public String getUpdatedDescription() {
        return "The first time you draw a Forgotten Card in your turn, draw another card.";
    }


    public AbstractRelic makeCopy() {
        return new LavaLamp();
    }

    public boolean usedThisTurn = false;

    public void atTurnStart() {
        System.out.println("atTurnStart called....");
        usedThisTurn = false;
    }

    public void onCardDraw (AbstractCard drawnCard) {
        if (!usedThisTurn && drawnCard instanceof ForgottenCard) {
            flash();
            addToBot(new DrawCardAction(1));
            usedThisTurn = true;
        }
    }
}