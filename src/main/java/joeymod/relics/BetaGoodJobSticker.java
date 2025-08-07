package joeymod.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import static joeymod.JoeyBasicMod.makeID;

// When you recollect and no cards in forgottenPile,: add a new card to your hand (cost 1 less?)
public class BetaGoodJobSticker extends AbstractSleeperRelic {
    public static final String ID = makeID("Blindfold");


    public BetaGoodJobSticker() {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL);
    }


    public AbstractRelic makeCopy() {
        return new BetaGoodJobSticker();
    }

    public void onRecollectWithNoForgotten() {
        addToBot(new MakeTempCardInHandAction(AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy(), false));
    }


}