package joeymod.powers;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static joeymod.JoeyBasicMod.makeID;

//if you payed 2+ to play a card last turn, draw an extra card this turn
public class EgoismPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(EgoismPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    AbstractPlayer p;

    int amount = 1;

    public EgoismPower(AbstractPlayer p, int amount) {
        super(POWER_ID, TYPE, false, p, amount);
        this.p = p;
        this.amount = amount;
        System.out.println(this.amount);
    }


}
