package joeymod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import joeymod.actions.SnoozePowerAction;

import static joeymod.JoeyBasicMod.makeID;


// Whenever you forget a card, draw a card.
public class SnoozePower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(SnoozePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public boolean usedThisTurn = false;

    public SnoozePower(AbstractCreature owner, int amount) {

        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        addToTop(new DrawCardAction(this.amount, new SnoozePowerAction(this.amount)));
        usedThisTurn = true;
    }
}
