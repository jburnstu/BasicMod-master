package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.cards.ForgottenCard;

import static sleepermod.SleeperMod.makeID;

//if you payed 2+ to play a card last turn, draw an extra card this turn
public class HappyPlacePower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(HappyPlacePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    AbstractPlayer p;

    int amount = 1;

    int blockFromCards = 0;

    public HappyPlacePower(AbstractPlayer p, int amount) {
        super(POWER_ID, TYPE, false, p, amount);
        this.p = p;
        this.amount = amount;
        System.out.println(this.amount);
    }

    public void onPlayerEndTurn() {
        System.out.println("Arrived at HappyPlaceUpdate...");
        blockFromCards = 0;
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ForgottenCard) {
                System.out.println("found a forgottenCard -- blockFromCards is " + blockFromCards + "and this.amount is " + this.amount);
                blockFromCards += this.amount;
            }
        }
        if (!(blockFromCards == 0)) {
            System.out.println("second if activated");
            flash();
            addToBot(new GainBlockAction(AbstractDungeon.player, null,blockFromCards));
        }
    }

}
