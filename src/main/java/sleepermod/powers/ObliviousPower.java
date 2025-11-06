package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import sleepermod.cards.ForgottenCard;

import static sleepermod.SleeperMod.makeID;

//if you payed 2+ to play a card last turn, draw an extra card this turn
public class ObliviousPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(ObliviousPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    AbstractPlayer owner;

    int amount = 1;

    int blockFromCards = 0;

    public ObliviousPower(AbstractPlayer owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
        this.owner = owner;
        this.amount = amount;
        System.out.println(this.amount);
    }

    @Override
    public void atEndOfTurnPreEndTurnCards(boolean isPlayer) {
        if (isPlayer) {
            blockFromCards = 0;
            for (AbstractCard c : this.owner.hand.group) {
                if (c instanceof ForgottenCard) {
                    System.out.println("found a forgottenCard -- blockFromCards is " + blockFromCards + "and this.amount is " + this.amount);
                    blockFromCards += this.amount;
                }
            }
            if (!(blockFromCards == 0)) {
                System.out.println("second if activated");
                flash();
                addToBot(new GainBlockAction(this.owner, null, blockFromCards));
            }
        }
    }
}
