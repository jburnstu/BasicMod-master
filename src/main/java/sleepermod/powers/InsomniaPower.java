package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static sleepermod.SleeperMod.makeID;


// Whenever you draw a forgotten card, gain 1 vigour.
public class InsomniaPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(InsomniaPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    public boolean reduceAtEnd = true;

    public InsomniaPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
        this.reduceAtEndOfTurn = reduceAtEnd;
    }

    @Override
    public void atStartOfTurn() {
        if (this.amount > this.owner.currentHealth) {
            addToBot(new LoseHPAction(this.owner, this.owner, this.owner.currentHealth));
        }
    }

    @Override
    public void onForget(AbstractCard c) {
        this.amount--;
    }
}
