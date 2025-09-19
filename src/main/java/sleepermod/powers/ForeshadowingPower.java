package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.WeakPower;

import static sleepermod.SleeperMod.makeID;


// Whenever you forget a card, draw a card.
public class ForeshadowingPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(ForeshadowingPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static final boolean removeAtEnd = true;

    public ForeshadowingPower(AbstractCreature owner, int amount) {

        super(POWER_ID, TYPE, TURN_BASED, owner, amount);
        this.removeAtEndOfTurn = removeAtEnd;
    }

    @Override
    public void onAwaken(AbstractCard card) {
        for (AbstractCreature mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToTop(new ApplyPowerAction(mo,this.owner,new WeakPower(mo,this.amount,false)));
        }
    }
}