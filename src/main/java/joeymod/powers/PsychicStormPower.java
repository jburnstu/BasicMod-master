package joeymod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static joeymod.JoeyBasicMod.makeID;

// Lose 3 strength. Whenever you remember an attack, the damage is dealt to ALL enemies.
public class PsychicStormPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(PsychicStormPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public PsychicStormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void onForget(AbstractCard card) {
        addToTop(new GainBlockAction(this.owner, this.amount));
    }

}