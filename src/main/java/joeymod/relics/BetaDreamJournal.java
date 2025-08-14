package joeymod.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import joeymod.actions.DreamJournalAction;

import static joeymod.JoeyBasicMod.makeID;

// Choose one card in hand at end of combat, starts next round in hand.
public class BetaDreamJournal extends AbstractSleeperRelic {
    public static final String ID = makeID("Blindfold");


    public BetaDreamJournal() {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL);
    }


    public AbstractRelic makeCopy() {
        return new BetaDreamJournal();
    }

    @Override
    public void onVictory () {
        addToBot(new DreamJournalAction());
    }


}