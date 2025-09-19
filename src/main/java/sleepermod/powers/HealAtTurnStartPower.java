package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static sleepermod.SleeperMod.makeID;


// Whenever you draw a forgotten card, gain 1 vigour.
public class HealAtTurnStartPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(HealAtTurnStartPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    public boolean reduceAtEndOfTurn = true;


    public HealAtTurnStartPower(AbstractCreature owner, AbstractCreature source, int amount) {

        super(POWER_ID, TYPE, false, owner, amount);
        this.source = source;
    }

    @Override
    public void atStartOfTurn() {
        if (this.amount > 0 ) {
            addToTop(new HealAction(this.owner,this.source,this.amount));
        }
        addToBot(new RemoveSpecificPowerAction(this.owner,this.source,this.ID));
        }



}