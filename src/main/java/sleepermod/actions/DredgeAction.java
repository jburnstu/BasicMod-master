package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.cards.ForgottenCard;

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