package joeymod.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import joeymod.powers.AbstractSleeperPower;

import static joeymod.JoeyBasicMod.makeID;

// Lose 2 strength. For 2 turns, whenever you remember an attack, the damage is dealt to ALL enemies.
public class PsychicStorm extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(PsychicStorm.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public PsychicStorm(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void onForget(AbstractCard card) {
        addToTop(new GainBlockAction(this.owner, this.amount));
    }

}