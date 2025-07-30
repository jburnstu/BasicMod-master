package joeymod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import joeymod.actions.RecollectAction;

import static joeymod.JoeyBasicMod.makeID;

// At the start of your turn, gain 1 amnesia and recall 1.
public class LucidDreamPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(LucidDreamPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public LucidDreamPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    public void atStartOfTurnPostDraw() {
        flash();
        addToBot((AbstractGameAction)new ApplyPowerAction(this.owner, this.owner, new AmnesiaPower(this.owner, this.amount), this.amount));
        addToBot((AbstractGameAction)new RecollectAction(this.amount,false));

    }
}