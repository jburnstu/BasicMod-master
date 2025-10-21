package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInDiscardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.actions.ForgetTopCardOfDiscardAction;
import sleepermod.actions.MakeTempForgottenCardInDiscardAction;
import sleepermod.cards.attacks.BrainZap;
import sleepermod.cards.statuses.Dizzy;

import static sleepermod.SleeperMod.makeID;

// retain a random forgotten card
public class BrainstormPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(BrainstormPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    AbstractPlayer p;

    int amount;
    int count = 0;

    public BrainstormPower(AbstractPlayer p, int amount) {
        super(POWER_ID, TYPE, false, p, amount);
        System.out.println("Reached SnoozePower Constructor.....");
        this.p = p;
        this.amount = amount;
        System.out.println(this.amount);
    }

    @Override
    public void atStartOfTurn() {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            flash();
            for (int i = 0; i < this.amount; i++) {
                addToBot(new MakeTempCardInDiscardAction(new BrainZap(), 1));
                addToBot(new ForgetTopCardOfDiscardAction(1));
            }
        }
    }
}

