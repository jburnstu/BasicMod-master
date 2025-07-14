package joeymod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static joeymod.JoeyBasicMod.makeID;

// All forgotten cards become urgent. Whenenver you play a forgotten card, gain 1 energy next turn.
public class DisseveredFormPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(DisseveredFormPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public DisseveredFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void onForget(AbstractCard card) {
        addToTop(new GainBlockAction(this.owner, this.amount));
    }

}