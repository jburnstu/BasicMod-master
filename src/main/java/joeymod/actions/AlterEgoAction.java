package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class AlterEgoAction extends AbstractGameAction {


    private float startingDuration = Settings.ACTION_DUR_FAST;

    public AlterEgoAction(){
        System.out.println("FA constructor called...");
        this.duration = startingDuration;
        this.actionType = ActionType.WAIT;
    }


    public void update() {
        System.out.println("entering AEA update....");
        if (this.duration == this.startingDuration) {
            System.out.println("yes on durations in AEA...");
            for (AbstractCard c : AbstractDungeon.player.hand.group) {
                System.out.println("inside AEA hand-loop....");
                if (c.type == AbstractCard.CardType.ATTACK)
                    addToTop(new ForgetSpecificCardAction(c, AbstractDungeon.player.hand));
                    System.out.println("Forgetcard action added by AEA....");
            }
            this.isDone = true;
        }
        tickDuration();
    }


}
