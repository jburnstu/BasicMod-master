package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.cards.ForgottenCard;
import sleepermod.powers.TrancePower;

public class DeepMemoryAction extends AbstractGameAction {
    public int passedBlock;
    public int passedMagicNumber;
    public AbstractCard communeToCopy;

    public DeepMemoryAction() {

    }

    @Override
    public void update() {
        if (this.target.hasPower(TrancePower.POWER_ID)) {
            addToTop(new DrawCardAction(1));
            if (!(DrawCardAction.drawnCards.get(0) instanceof ForgottenCard)) {
                addToTop(new DeepMemoryAction());
            }
            this.isDone = true;
            return;
        }
        else {
            addToBot(new ApplyPowerAction(this.target,this.target,new TrancePower(this.target,1)));
        }
        this.isDone = true;
    }
}