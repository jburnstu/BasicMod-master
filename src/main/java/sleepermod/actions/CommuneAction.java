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

public class CommuneAction extends AbstractGameAction {
    public int passedBlock;
    public int passedMagicNumber;
    public AbstractCard communeToCopy;

    public CommuneAction(AbstractPlayer target, int passedBlock,int passedMagicNumber, AbstractCard cardToCopy) {
        this.target = target;
        this.passedBlock = passedBlock;
        this.passedMagicNumber = passedMagicNumber;
        this.communeToCopy = cardToCopy;
    }

    @Override
    public void update() {
        addToBot(new GainBlockAction(this.target,this.passedBlock));
        addToBot(new DrawCardAction(1));
        System.out.println("Commune Action drawn, card is " + DrawCardAction.drawnCards.get(0));
        if (DrawCardAction.drawnCards.get(0) instanceof ForgottenCard) {
            System.out.println("COmmune loop entered....");
            AbstractCard tmp = this.communeToCopy.makeSameInstanceOf();
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, null, tmp.energyOnUse, true, true), true);
        }
        this.isDone = true;
    }
}