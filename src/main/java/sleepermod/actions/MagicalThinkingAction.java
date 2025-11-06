package sleepermod.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static com.megacrit.cardcrawl.actions.defect.RecycleAction.TEXT;

public class MagicalThinkingAction extends AbstractGameAction {
    private AbstractCard calledByThisCard;
    public AbstractPlayer p;

    private float startingDuration;

    public MagicalThinkingAction(AbstractCard calledByThisCard) {
        setValues(AbstractDungeon.player, AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.p = AbstractDungeon.player;
        this.calledByThisCard = calledByThisCard;
    }

    public void update () {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.size() == 1) {
                AbstractCard targetCard = this.p.hand.getBottomCard();
                int changeThisCardBy = 0;
                if (targetCard.costForTurn == -1) {
                    changeThisCardBy = -this.calledByThisCard.cost;
                } else {
                    changeThisCardBy = targetCard.costForTurn - this.calledByThisCard.cost;
                }
                this.calledByThisCard.modifyCostForCombat(changeThisCardBy);
                targetCard.modifyCostForCombat(-changeThisCardBy);
                tickDuration();
                return;
            }
            AbstractDungeon.handCardSelectScreen.open("Select a card to swap costs with.", 1, false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractCard targetCard = AbstractDungeon.handCardSelectScreen.selectedCards.getBottomCard();
            int changeThisCardBy = 0;
            if (targetCard.costForTurn == -1) {
                changeThisCardBy = -this.calledByThisCard.cost;
            } else {
                changeThisCardBy = targetCard.costForTurn - this.calledByThisCard.cost;
            }
            this.calledByThisCard.modifyCostForCombat(changeThisCardBy);
            targetCard.modifyCostForCombat(-changeThisCardBy);
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
        }
        tickDuration();
    }
}
