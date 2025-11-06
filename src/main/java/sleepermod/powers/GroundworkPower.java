package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import sleepermod.actions.AwakenAction;

import static sleepermod.SleeperMod.makeID;

// retain a random forgotten card
public class GroundworkPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(GroundworkPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    AbstractPlayer p;

    int amount = 1;

    public GroundworkPower(AbstractPlayer p, int amount) {
        super(POWER_ID, TYPE, false, p, amount);
        System.out.println("Reached SnoozePower Constructor.....");
        this.p = p;
        this.amount = amount;
        System.out.println(this.amount);
    }

    @Override
    public void atStartOfTurn() {
        addToBot(new AwakenAction(this.amount,false));
        addToTop(new RemoveSpecificPowerAction(this.p,this.p,POWER_ID));
    }
}

