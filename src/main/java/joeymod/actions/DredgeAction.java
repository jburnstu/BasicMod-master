package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import joeymod.cards.ForgottenCard;
import joeymod.powers.VisionPower;

public class DredgeAction extends AbstractGameAction {
    public int passedBlock;
    public int passedMagicNumber;
    public AbstractCard communeToCopy;

    public DredgeAction() {
    }

    @Override
    public void update() {
        for (AbstractCard c : AbstractDungeon.player.drawPile.group) {
            if (!(c instanceof ForgottenCard)) {
                AbstractDungeon.player.drawPile.removeCard(c);
                AbstractDungeon.player.hand.addToTop(c);
                this.isDone = true;
                break;
            }
        }
    }
}