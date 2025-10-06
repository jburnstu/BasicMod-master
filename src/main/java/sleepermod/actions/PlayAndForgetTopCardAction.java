package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.common.PlayTopCardAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.patches.coremechanics.FieldPatchAbstractCardBackForgottenCard;

public class PlayAndForgetTopCardAction extends PlayTopCardAction {
//    private boolean playFirst;

    public PlayAndForgetTopCardAction(AbstractCreature target) {
        super(target,false);
//        this.playFirst = playFirst;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (AbstractDungeon.player.drawPile.size() + AbstractDungeon.player.discardPile.size() == 0) {
                this.isDone = true;
                return;
            }
            if (AbstractDungeon.player.drawPile.isEmpty()) {
                addToTop(new PlayAndForgetTopCardAction(this.target));
                addToTop(new EmptyDeckShuffleAction());
                this.isDone = true;
                return;
            }
        }
        if (this.duration == Settings.ACTION_DUR_FAST) {
            FieldPatchAbstractCardBackForgottenCard.forgetOnUseOnce.set(AbstractDungeon.player.drawPile.getTopCard(),true);
        }
        super.update();
    }
}
