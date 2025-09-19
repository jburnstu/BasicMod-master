package sleepermod.relics;

import com.megacrit.cardcrawl.relics.AbstractRelic;

import static sleepermod.SleeperMod.makeID;

public class LavaLamp extends AbstractSleeperRelic {
    public static final String ID = makeID(LavaLamp.class.getSimpleName());

    public LavaLamp() {
        super(ID, AbstractRelic.RelicTier.UNCOMMON, AbstractRelic.LandingSound.MAGICAL);
    }

    public AbstractRelic makeCopy() {
        return new LavaLamp();
    }

}