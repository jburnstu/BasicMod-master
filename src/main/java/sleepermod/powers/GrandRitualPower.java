package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import sleepermod.actions.AwakenAction;

import static sleepermod.SleeperMod.makeID;

// retain a random forgotten card
public class GrandRitualPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(GrandRitualPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    AbstractPlayer p;

    int amount = 1;
    boolean upgraded = false;

    public GrandRitualPower(AbstractPlayer p, int amount) {
        super(POWER_ID, TYPE, false, p, amount);
        System.out.println("Reached DaydreamPower Constructor.....");
        this.p = p;
        this.amount = amount;
        this.reduceAtEndOfTurn = false;
    }

    @Override
    public void atStartOfTurn() {
        this.reduceAtEndOfTurn = true;
        addToBot(new ApplyPowerAction(p,p,new TrancePower(p,1)));
        addToBot(new DrawCardAction(1));
    }
}

