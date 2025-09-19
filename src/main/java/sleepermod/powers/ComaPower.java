package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import sleepermod.cards.ForgottenCard;

import static sleepermod.SleeperMod.makeID;


public class ComaPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(ComaPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = true;
    public static final boolean removeAtEnd = true;

    public ComaPower(AbstractCreature owner, int amount) {

        super(POWER_ID, TYPE, true, owner, amount);
        this.removeAtEndOfTurn = removeAtEnd;
    }


    @Override
    public void onCardDraw(AbstractCard c) {
        if (!(c instanceof ForgottenCard)) {
            addToBot(new DiscardSpecificCardAction(c));
        }
    }

}
