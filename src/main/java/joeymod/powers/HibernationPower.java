package joeymod.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static joeymod.JoeyBasicMod.makeID;


// Whenever you forget a card, draw a card.
public class HibernationPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(HibernationPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    public boolean usedThisTurn = false;

    public HibernationPower(AbstractCreature owner, int amount) {

        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
    }



    @Override
    public void onCardDraw(AbstractCard card) {
        if (AbstractDungeon.player.drawPile.isEmpty()) {
            flash();
            addToBot(new LoseHPAction(this.owner, this.owner, 99999));
            addToBot(new RemoveSpecificPowerAction(this.owner, this.owner, "HibernationPower"));
        }
    }

}