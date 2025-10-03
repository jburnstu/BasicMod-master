package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static sleepermod.SleeperMod.makeID;


// Whenever you draw a forgotten card, gain 1 vigour.
public class ChosenOnePower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(ChosenOnePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public AbstractCreature source;

    public ChosenOnePower(AbstractCreature owner, AbstractCreature source, int amount) {

        super(POWER_ID, TYPE, false, owner, amount);
        this.source = source;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (target == this.source && damageAmount > 0 && info.type == DamageInfo.DamageType.NORMAL) {
            addToTop(new ApplyPowerAction(this.source, this.source, new TrancePower(this.source, 1)));
            }
    }
}