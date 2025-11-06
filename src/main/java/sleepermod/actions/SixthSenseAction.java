package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.ForgottenCard;
import sleepermod.powers.TrancePower;

public class SixthSenseAction extends AbstractGameAction {
    private AbstractPlayer p = AbstractDungeon.player;

    public SixthSenseAction(int visionAmt) {
        this.actionType = ActionType.WAIT;
        this.amount = visionAmt;
    }

    public void update() {
        for (AbstractCard c : p.hand.group) {
            if (c instanceof ForgottenCard) {
                addToTop(new ApplyPowerAction(p, p, new TrancePower(p, 1)));
                break;
            }
            this.isDone = true;
        }
    }
}