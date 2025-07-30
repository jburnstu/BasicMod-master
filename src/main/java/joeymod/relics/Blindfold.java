package joeymod.relics;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;

// When you recollect and no cards in forgottenPile,: add a new card to your hand, cost 1 less
public class Blindfold extends AbstractSleeperRelic {
    public static final String ID = makeID("Blindfold");



    public Blindfold() {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL);
    }


    public AbstractRelic makeCopy() {
        return new Blindfold();
    }

    public void onRecollectWithNoForgotten() {
        // create random card;
        // AbstractDungeon.player.hand.addToTop(random);
        // random.reduceCostThisTurn(1);
    }


}