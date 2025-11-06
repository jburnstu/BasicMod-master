package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import sleepermod.powers.InsomniaPower;

public class EnterDreamsAction extends AbstractGameAction {
    private DamageInfo info;

    public EnterDreamsAction(AbstractCreature target, DamageInfo info) {
        this.actionType = ActionType.DAMAGE;
        this.target = target;
        this.info = info;
    }

    public void update() {
        for (AbstractPower power: this.target.powers) {
            if (power.ID.equals(InsomniaPower.POWER_ID)) {
                for (int i=0; i < power.amount; i++) {
                    addToTop(new DamageAction(this.target, this.info, AttackEffect.BLUNT_HEAVY));
                }
                addToBot(new ApplyPowerAction(this.target,null,new WeakPower(this.target,power.amount,false)));
            }
        }
        this.isDone = true;
    }
}