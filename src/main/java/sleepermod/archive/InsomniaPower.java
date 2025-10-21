package sleepermod.archive;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.ForgottenCard;
import sleepermod.powers.AbstractSleeperPower;

import static sleepermod.SleeperMod.makeID;



public class InsomniaPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(InsomniaPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    public boolean reduceAtEnd = false;

    public InsomniaPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof ForgottenCard && this.owner == m) {
            flash();
            addToBot(new ApplyPowerAction(this.owner, this.owner, new sleepermod.powers.InsomniaPower(m, this.amount)));
        }
    }
}