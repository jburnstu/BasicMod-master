package sleepermod.relics;

import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.actions.common.UpgradeSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static sleepermod.SleeperMod.makeID;

// When you recollect and no cards in forgottenPile,: add a new card to your hand (cost 1 less?)
public class GoodJobSticker extends AbstractSleeperRelic {
    public static final String ID = makeID(GoodJobSticker.class.getSimpleName());
    private static final RelicTier RARITY = RelicTier.RARE; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.


    public GoodJobSticker() {
        super(ID, RARITY, SOUND);
    }


    public AbstractRelic makeCopy() {
        return new GoodJobSticker();
    }

    @Override
    public void onAwaken(AbstractCard card) {
        addToTop(new UpgradeSpecificCardAction(card));
    }

    public boolean usedThisTurn = false;

    @Override
    public void atTurnStart() {
        usedThisTurn = false;
    }


//    @Override
//    public void onAwaken(AbstractCard card) {
//        if (!usedThisTurn) {
//            addToTop(new ReduceCostForTurnAction(card, 1));
//            usedThisTurn = true;
//        }
//    }
}