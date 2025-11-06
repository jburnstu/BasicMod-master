package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.powers.InsomniaPower;

public class AllEnemiesLoseEqualToInsomniaAction extends AbstractGameAction {

    @Override
    public void update() {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            if (mo.hasPower(InsomniaPower.POWER_ID)) {
                addToTop(new LoseHPAction(mo, AbstractDungeon.player, mo.getPower(InsomniaPower.POWER_ID).amount));
            }
        }
        this.isDone = true;
    }
}
