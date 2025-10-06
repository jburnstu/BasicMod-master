package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.powers.TrancePower;

public class PrecogAction extends AbstractGameAction {
    private AbstractMonster m;

    public PrecogAction(int visionAmt, AbstractMonster m) {
        this.actionType = AbstractGameAction.ActionType.WAIT;
        this.amount = visionAmt;
        this.m = m;
    }

    public void update() {
        if (this.m != null && this.m.getIntentBaseDmg() >= 0) {
            addToTop(new AwakenAction(1,false));
        }
        this.isDone = true;
    }
}