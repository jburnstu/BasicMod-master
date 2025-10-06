package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import sleepermod.cards.ForgottenCard;

import static sleepermod.SleeperMod.makeID;

public class TrancePower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(TrancePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public static boolean removeAtEnd = false;

    public TrancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
        this.removeAtEndOfTurn = removeAtEnd;
        this.canGoNegative = false;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof ForgottenCard) {
            flash();
            this.amount--;
            if (this.amount == 0) {
                addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
            }
        }
    }
}
