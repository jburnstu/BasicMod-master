package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReduceCostForTurnAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class VisionAction extends AbstractGameAction {

    private boolean reduceCost;

    public AbstractPlayer p;

    public VisionAction(boolean reduceCost) {
        System.out.println("FA constructor called...");
        this.reduceCost = reduceCost;
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        if (this.reduceCost) {
            addToTop(new ReduceCostForTurnAction(AwakenAction.awakenedCards.get(0), 1));
        }
        this.isDone = true;
    }
}