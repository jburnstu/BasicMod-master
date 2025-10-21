package sleepermod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.powers.TrancePower;

import static sleepermod.SleeperMod.makeID;

// Choose one card in hand at end of combat, starts next round in hand.
public class EyeMask extends AbstractSleeperRelic {
    public static final String ID = makeID(EyeMask.class.getSimpleName());
    private AbstractPlayer p = AbstractDungeon.player;

    public EyeMask() {
        super(ID, RelicTier.COMMON, LandingSound.MAGICAL);
    }


    public AbstractRelic makeCopy() {
        return new EyeMask();
    }


    @Override
    public void atBattleStartPreDraw() {
        addToBot(new ApplyPowerAction(p,p, new TrancePower(p,1)));
    }
}