package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import joeymod.cards.ForgottenCard;

import javax.swing.text.html.HTMLDocument;

public class IgnoranceIsStrengthAction extends AbstractGameAction {
    public int passedBlock;

    public IgnoranceIsStrengthAction(AbstractPlayer target, int passedBlock) {
        this.target = target;
        this.passedBlock = passedBlock;
    }

    @Override
    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ForgottenCard) {
                addToTop((AbstractGameAction)new GainBlockAction(this.target, this.passedBlock));
                addToBot((AbstractGameAction)new DrawCardAction(this.target, 1));
            }
        }
        this.isDone = true;
    }
}
