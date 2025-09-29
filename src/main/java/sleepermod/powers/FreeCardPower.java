package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;
import org.lwjgl.Sys;

import static sleepermod.SleeperMod.makeID;

public class FreeCardPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(FreeCardPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;
    private static final boolean removeAtEnd = true;
    AbstractPlayer owner;
    int amount;

    public FreeCardPower(AbstractPlayer p, int amount) {
        super(POWER_ID, TYPE, TURN_BASED, p, amount);
        this.owner = p;
        this.amount = amount;
        this.removeAtEndOfTurn = removeAtEnd;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        System.out.println("FreeCardPower onUseCard used");
        if (!card.purgeOnUse && this.amount > 0) {
            System.out.println("FreeCardPower IF passed");
            flash();
            this.amount--;
            if (this.amount == 0) {
                System.out.println("FreeCardPower second IF passed");
                addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, POWER_ID));
            }
        }
    }
}