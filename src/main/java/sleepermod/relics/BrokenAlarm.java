package sleepermod.relics;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.powers.InsomniaPower;
import sleepermod.powers.TrancePower;

import static sleepermod.SleeperMod.makeID;

// Choose one card in hand at end of combat, starts next round in hand.
public class BrokenAlarm extends AbstractSleeperRelic {
    public static final String ID = makeID(BrokenAlarm.class.getSimpleName());
    private AbstractPlayer p = AbstractDungeon.player;

    public BrokenAlarm() {
        super(ID, RelicTier.COMMON, LandingSound.MAGICAL);
    }


    public AbstractRelic makeCopy() {
        return new BrokenAlarm();
    }


    @Override
    public void atTurnStart() {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, p, new InsomniaPower(mo, 1)));
        }
    }

}