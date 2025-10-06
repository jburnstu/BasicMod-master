package sleepermod.relics.deprecated;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.relics.AbstractSleeperRelic;

import static sleepermod.SleeperMod.makeID;

// When you recollect and no cards in forgottenPile,: add a new card to your hand (cost 1 less?)
public class ArchiveGoodJobSticker extends AbstractSleeperRelic {
    public static final String ID = makeID(ArchiveGoodJobSticker.class.getSimpleName());
    private static final RelicTier RARITY = RelicTier.COMMON; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.


    public ArchiveGoodJobSticker() {
        super(ID, RARITY, SOUND);
    }


    public AbstractRelic makeCopy() {
        return new ArchiveGoodJobSticker();
    }

    public void onRecollectWithNoForgotten() {
        addToBot(new MakeTempCardInHandAction(AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy(), false));
    }


}