package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sleepermod.actions.MakeTempForgottenCardInDiscardAction;
import sleepermod.cards.ForgottenCard;

import static sleepermod.SleeperMod.makeID;

// retain a random forgotten card
public class DaydreamPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(DaydreamPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

    AbstractPlayer p;

    int amount;
    int count = 0;

    public DaydreamPower(AbstractPlayer p, int amount) {
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
            for (int i = 0; i < this.amount; i++)
                addToBot(new MakeTempForgottenCardInDiscardAction(
                        AbstractDungeon.getCard(AbstractCard.CardRarity.COMMON, AbstractDungeon.cardRandomRng)
                                .makeCopy(), 1));
        }
    }
}

