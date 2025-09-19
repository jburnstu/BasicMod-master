package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import sleepermod.actions.ForgetAction;

import static sleepermod.SleeperMod.makeID;


// Whenever you forget a card, draw a card.
public class CircadianRhythmPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(CircadianRhythmPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public CircadianRhythmPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        addToTop(new DrawCardAction(this.amount));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        addToTop(new ForgetAction(this.amount, false, false, false));
    }
}
