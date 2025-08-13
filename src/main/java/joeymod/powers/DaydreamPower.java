package joeymod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;

// retain a random forgotten card
public class DaydreamPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(DaydreamPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    AbstractPlayer p;

    int amount = 1;

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
        int count = 0;
        while (count < this.amount) {
            for (AbstractCard c:  p.hand.group) {
                if (c instanceof ForgottenCard) {
                    c.retain = true;
                    count += 1;
                }
            }
        }
    }
}
