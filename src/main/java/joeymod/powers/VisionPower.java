package joeymod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;

public class VisionPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(VisionPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public static boolean removeAtEnd = true;

    public VisionPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
        this.removeAtEndOfTurn = removeAtEnd;
    }

public void onUseCard(AbstractCard card, UseCardAction action) {
    if (card instanceof ForgottenCard && !card.purgeOnUse && this.amount > 0) {
        flash();
        this.amount--;
        if (this.amount == 0)
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "FreeAttackPower"));
        }
    }
}
