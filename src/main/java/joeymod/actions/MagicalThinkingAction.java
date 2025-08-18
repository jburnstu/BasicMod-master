package joeymod.actions;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import joeymod.character.MySleeperPlayer;

import static com.megacrit.cardcrawl.actions.defect.RecycleAction.TEXT;

public class MagicalThinkingAction extends AbstractGameAction {
    private AbstractCard calledByThisCard;
    private AbstractCard targetCard;
    public AbstractPlayer p;

    private float startingDuration;

    public MagicalThinkingAction(AbstractCard calledByThisCard) {
        setValues((AbstractCreature) AbstractDungeon.player, (AbstractCreature) AbstractDungeon.player, this.amount);
        this.actionType = ActionType.CARD_MANIPULATION;
        this.startingDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startingDuration;
        this.p = AbstractDungeon.player;
    }

    public void update () {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (this.p.hand.isEmpty()) {
                this.isDone = true;
                return;
            }
            if (this.p.hand.size() == 1) {
                AbstractCard targetCard = this.p.hand.getBottomCard();
                if ((targetCard).costForTurn == -1) {
                    this.calledByThisCard.modifyCostForCombat(-this.calledByThisCard.cost);
                } else {
                    this.calledByThisCard.modifyCostForCombat(targetCard.costForTurn - this.calledByThisCard.costForTurn);
                    targetCard.modifyCostForCombat(this.calledByThisCard.costForTurn - targetCard.costForTurn);
                }
                tickDuration();
                return;
            }
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], 1, false);
            tickDuration();
            return;
        }
        if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractCard targetCard = AbstractDungeon.handCardSelectScreen.selectedCards.getBottomCard();
            if ((this.p.hand.getBottomCard()).costForTurn == -1) {
                this.calledByThisCard.modifyCostForCombat(-this.calledByThisCard.cost);
            } else {
                this.calledByThisCard.modifyCostForCombat(targetCard.costForTurn - this.calledByThisCard.costForTurn);
                targetCard.modifyCostForCombat(this.calledByThisCard.costForTurn - targetCard.costForTurn);
            }

            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            AbstractDungeon.handCardSelectScreen.selectedCards.group.clear();
        }
        tickDuration();
    }
}
