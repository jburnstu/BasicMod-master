package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.ForgottenCard;

import static sleepermod.SleeperMod.makeID;

public class TrancePower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(TrancePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    public static boolean removeAtEnd = true;

    public TrancePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
        this.removeAtEndOfTurn = removeAtEnd;
        this.canGoNegative = false;
    }

    @Override
    public void onRemember(AbstractCard card, AbstractCreature m) {
        flash();
        this.amount--;
        if (this.amount == 0) {
            addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, this));
        }
    }


//    @Override
//    public void onRemember(AbstractCard c, AbstractCreature m) {
//        flash();
//        for (int i=0 ; i<this.amount-1 ; i++) {
//            AbstractCard tmp = c.makeSameInstanceOf();
//            AbstractDungeon.player.limbo.addToBottom(tmp);
//            tmp.current_x = c.current_x + i * 20.0F;
//            tmp.current_y = c.current_y;
//            tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
//            tmp.target_y = Settings.HEIGHT / 2.0F;
//            if (m != null)
//                tmp.calculateCardDamage((AbstractMonster) m);
//            tmp.purgeOnUse = true;
//            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, (AbstractMonster) m, c.energyOnUse, true, true), true);
//        }
//        addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, this));
//    }
}
