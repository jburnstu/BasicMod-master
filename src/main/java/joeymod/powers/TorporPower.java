package joeymod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static joeymod.JoeyBasicMod.makeID;
// Whenever you remember a skill, apply 1 weak to all enemies.
public class TorporPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(TorporPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public TorporPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void atStartOfTurn() {
        flash();
        for (AbstractCreature mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToBot(new ApplyPowerAction(mo, AbstractDungeon.player, new WoozyPower(mo, this.amount)));
        }
    }

}