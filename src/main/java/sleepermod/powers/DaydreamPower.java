package sleepermod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import sleepermod.cards.ForgottenCard;

import static sleepermod.SleeperMod.makeID;

// retain a random forgotten card
public class DaydreamPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(DaydreamPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    AbstractPlayer p;

    int amount = 1;
    int count = 0;

    public DaydreamPower(AbstractPlayer p, int amount) {
        super(POWER_ID, TYPE, false, p, amount);
        System.out.println("Reached DaydreamPower Constructor.....");
        this.p = p;
        this.amount = amount;
        System.out.println(this.amount);
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        //make this random somehow
        count = 0;
        System.out.println("count: " + count+ "this.amount: " + this.amount);
        for (AbstractCard c:  p.hand.group) {
            if (count < 1 && c instanceof ForgottenCard) {
                c.retain = true;
                count += 1;
                System.out.println(count);
            }
        }
    }
}

