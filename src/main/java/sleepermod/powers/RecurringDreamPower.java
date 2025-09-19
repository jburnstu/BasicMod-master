package sleepermod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
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


    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof ForgottenCard) {
            AbstractCard tmp = ((ForgottenCard) c).frontForgottenCard.makeSameInstanceOf();
            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.addCardQueueItem(new CardQueueItem(tmp, m, tmp.energyOnUse, true, true), true);
        }
    }


}
