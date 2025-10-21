package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.DiscardSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import sleepermod.cards.ForgottenCard;

import static sleepermod.SleeperMod.makeID;


public class EnterTheVoidPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(EnterTheVoidPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = true;
    public static final boolean removeAtEnd = true;

    public EnterTheVoidPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.removeAtEndOfTurn = removeAtEnd;
    }

    //This Power acts in RememberAction to skip the whole action.

}
