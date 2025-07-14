package joeymod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

import static joeymod.JoeyBasicMod.makeID;
// Whenever you remember a skill, apply 1 weak to all enemies.
public class SubliminalPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(SubliminalPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public SubliminalPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void onForget(AbstractCard card) {
        addToTop(new GainBlockAction(this.owner, this.amount));
    }

}