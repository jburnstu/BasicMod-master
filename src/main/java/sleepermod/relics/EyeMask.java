package sleepermod.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.actions.DreamJournalAction;

import static sleepermod.SleeperMod.makeID;

// Choose one card in hand at end of combat, starts next round in hand.
public class EyeMask extends AbstractSleeperRelic {
    public static final String ID = makeID(EyeMask.class.getSimpleName());


    public EyeMask() {
        super(ID, RelicTier.STARTER, LandingSound.MAGICAL);
    }


    public AbstractRelic makeCopy() {
        return new EyeMask();
    }


//    @Override
//    public void onVictory () {
//        addToBot(new DreamJournalAction());
//    }


}