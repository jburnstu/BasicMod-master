package sleepermod.relics;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.cards.ForgottenCard;

import java.util.ArrayList;

import static sleepermod.SleeperMod.makeID;


// Gain 2 block at end of turn for every forgotten card in your hand.
public class SleepingMouse extends AbstractSleeperRelic {
    public static final String ID = makeID(SleepingMouse.class.getSimpleName());
    private static final RelicTier RARITY = RelicTier.RARE; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.CLINK; //The sound played when the relic is clicked.

    public SleepingMouse() {
        super(ID, RARITY,SOUND);
    }

    public int blockAmt = 3;

    public AbstractRelic makeCopy() {
        return new SleepingMouse();
    }

    @Override
    public void onAwaken(AbstractCard card) {
        addToTop(new GainBlockAction(AbstractDungeon.player, blockAmt));
    }
}