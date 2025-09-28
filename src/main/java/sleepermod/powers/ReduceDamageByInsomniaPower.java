package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static sleepermod.SleeperMod.makeID;

public class ReduceDamageByInsomniaPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(RecurringDreamPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    boolean removeAtEndOfTurn = true;

    public ReduceDamageByInsomniaPower(AbstractPlayer owner, int amount) {
        super(POWER_ID, TYPE, false, owner,owner, amount,true, true, false,true);

    }

    public int reducedDamage;

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner.hasPower(InsomniaPower.POWER_ID)) {
            AbstractPower targetInsomnia = info.owner.getPower(InsomniaPower.POWER_ID);
            if (targetInsomnia.amount < damageAmount) {
                reducedDamage = damageAmount - info.owner.getPower(InsomniaPower.POWER_ID).amount;
                addToTop(new RemoveSpecificPowerAction(info.owner, this.owner, targetInsomnia));
            } else if (targetInsomnia.amount >= damageAmount) {
                reducedDamage = 0;
                addToTop(new ReducePowerAction(info.owner, this.owner, targetInsomnia, targetInsomnia.amount));
            }
            return super.onAttacked(info, reducedDamage);
        }
        return super.onAttacked(info,damageAmount);
    }
}
