package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;

public class EpiphanyAction extends AbstractGameAction {

    private int playAmt;

    public EpiphanyAction(int playAmt) {
        System.out.println("FA constructor called...");
        this.playAmt = playAmt;
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        for (AbstractCard c : AwakenAction.awakenedCards) {
            c.exhaust = true;
            AbstractDungeon.player.hand.group.remove(c);
            (AbstractDungeon.getCurrRoom()).souls.remove(c);
            addToBot((AbstractGameAction) new NewQueueCardAction(c, true, false, true));
            for (int i = 0; i < this.playAmt - 1; i++) {
                AbstractCard tmp = c.makeStatEquivalentCopy();
                tmp.purgeOnUse = true;
                addToBot((AbstractGameAction) new NewQueueCardAction(tmp, true, false, true));
            }
        }
        this.isDone = true;
    }
}