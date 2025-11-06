package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.ForgottenCard;

import static sleepermod.SleeperMod.makeID;

// Whenever you remember a skill, apply 1 weak to all enemies.
public class TheatreOfTheMindPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(TheatreOfTheMindPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public TheatreOfTheMindPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
        this.reduceAtEndOfTurn = true;
    }

    @Override
    public void onRemember(AbstractCard c, AbstractCreature m) {
            addToBot(new ApplyPowerAction(this.owner,this.owner, new TrancePower(this.owner,1)));
    }
}