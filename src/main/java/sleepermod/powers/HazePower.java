package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import static sleepermod.SleeperMod.makeID;

// deal woozy to attacking enemies
public class HazePower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(HazePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    AbstractPlayer p;

    int amount = 1;

    public HazePower(AbstractPlayer p, int amount) {
        super(POWER_ID, TYPE, false, p, amount);
        System.out.println("Reached SnoozePower Constructor.....");
        this.p = p;
        this.amount = amount;
        System.out.println(this.amount);
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != null && info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != this.owner) {
            flash();
            addToTop(new ApplyPowerAction(info.owner,this.p,new InsomniaPower(info.owner,this.amount)));
        }
        return damageAmount;
    }

    @Override
    public void atStartOfTurn() {
        addToTop(new RemoveSpecificPowerAction(this.p,this.p,POWER_ID));
    }
}

