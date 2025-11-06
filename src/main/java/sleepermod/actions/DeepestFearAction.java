package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import sleepermod.powers.InsomniaPower;

public class DeepestFearAction extends AbstractGameAction {
    private int amountPerInsomnia;

    public DeepestFearAction(AbstractCreature target, int amountPerInsomnia) {
        this.actionType = ActionType.DAMAGE;
        this.target = target;
        this.amountPerInsomnia = amountPerInsomnia;
    }

    public void update() {
        for (AbstractPower power: this.target.powers) {
            if (power.ID.equals(InsomniaPower.POWER_ID)) {
                addToBot(new ApplyPowerAction(this.target,null,new VulnerablePower(this.target,power.amount * this.amountPerInsomnia,false)));
            }
        }
        this.isDone = true;
    }
}