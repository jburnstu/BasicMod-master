package joeymod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import joeymod.cards.ForgottenCard;

public abstract class TeddyBear extends AbstractRelic {


    public TeddyBear(String setId, String imgName, RelicTier tier, LandingSound sfx) {
        super(setId, imgName, tier, sfx);
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