package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.powers.InsomniaPower;

public class MultiplyPowerAction extends AbstractGameAction {

    public String targetPowerID;

    public MultiplyPowerAction(AbstractCreature target, AbstractCreature source, String powerID, int amount) {
        this.target = target;
        this.source = source;
        this.targetPowerID = powerID;
        this.amount = amount;
    }

    public void update() {
        if (this.targetPowerID.equals(InsomniaPower.POWER_ID) && this.target.hasPower(this.targetPowerID))
            addToTop(new ApplyPowerAction(this.target, this.source, new InsomniaPower(this.target,
                    (this.target.getPower(this.targetPowerID)).amount * (this.amount - 1)),
                    (this.target.getPower(this.targetPowerID)).amount * (this.amount - 1)));
        this.isDone = true;
    }
}
