package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AlterEgoAction extends AbstractGameAction {


    private float startingDuration = Settings.ACTION_DUR_FAST;

    public void update() {
        if (this.duration == this.startingDuration) {
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                if (c.type == AbstractCard.CardType.ATTACK)
                    addToTop(new ForgetSpecificCardAction(c, AbstractDungeon.player.hand));
            }
            this.isDone = true;
        }
        tickDuration();
    }


}
