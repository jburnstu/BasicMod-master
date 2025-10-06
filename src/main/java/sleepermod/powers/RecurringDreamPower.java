package sleepermod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import sleepermod.cards.ForgottenCard;

import static sleepermod.SleeperMod.makeID;

public class RecurringDreamPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(RecurringDreamPower.class.getSimpleName());
    private static final AbstractPower.PowerType TYPE = AbstractPower.PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    boolean removeAtEndOfTurn = true;

    public RecurringDreamPower(AbstractPlayer owner, int amount) {
        super(POWER_ID, TYPE, false, owner,owner, amount,true, true, false,true);
    }

    @Override
    public void onUseCard(AbstractCard c, UseCardAction action) {
        if (c instanceof ForgottenCard && this.amount > 0) {
            flash();
            AbstractMonster m = null;
            if (action.target != null)
                m = (AbstractMonster)action.target;
            AbstractCard tmp = ((ForgottenCard) c).frontForgottenCard.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = c.current_x;
            tmp.current_y = c.current_y;
            tmp.target_x = Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = Settings.HEIGHT / 2.0F;
            if (m != null)
                tmp.calculateCardDamage(m);
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, c.energyOnUse, true, true), true);
            this.amount--;
            if (this.amount == 0) {
                addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, RecurringDreamPower.POWER_ID));
            }
        }
    }

}
