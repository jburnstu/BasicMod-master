package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class SleepNoMoreAction extends AbstractGameAction {
    private DamageInfo info;

    public SleepNoMoreAction(AbstractCreature target, int amount) {
        this.actionType = ActionType.DAMAGE;
        this.target = target;
        this.amount = amount;
    }

    public void update() {
        for (AbstractPower power: this.target.powers) {
            if (power.ID.equals("InsomniaPower")) {
                int damageFromWoozy = this.amount * power.amount;
                addToTop(new LoseHPAction(this.target, AbstractDungeon.player, damageFromWoozy));
                addToTop(new RemoveSpecificPowerAction(this.target,AbstractDungeon.player,power));
            }
        }
        this.isDone = true;
    }
}