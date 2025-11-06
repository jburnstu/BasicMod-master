package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FlashbackAction extends AbstractGameAction {

    private int playAmt;

    public FlashbackAction(int playAmt) {
        System.out.println("FA constructor called...");
        this.playAmt = playAmt;
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        if (AbstractDungeon.actionManager.cardsPlayedThisCombat.size() >= 2) {
            AbstractCard c = AbstractDungeon.actionManager.cardsPlayedThisCombat
                    .get(AbstractDungeon.actionManager.cardsPlayedThisCombat
                            .size() - 2);
            AbstractCard tmp = c.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = c.current_x;
            tmp.current_y = c.current_y;
            tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = Settings.HEIGHT / 2.0F;
            AbstractMonster m = null;
            if (m != null)
                tmp.calculateCardDamage((AbstractMonster) m);
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, (AbstractMonster) m, c.energyOnUse, true, true), true);
        }
    }
}