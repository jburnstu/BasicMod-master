package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import sleepermod.cards.ForgottenCard;

import static sleepermod.SleeperMod.makeID;

public class AmnesiaPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(AmnesiaPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public AmnesiaPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void onRemember(AbstractCard c, AbstractCreature m) {
        flash();
        c.exhaustOnUseOnce = true;
        this.amount--;
        if (this.amount == 0)
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
    }
}
