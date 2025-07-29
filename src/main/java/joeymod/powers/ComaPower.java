package joeymod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;


public class ComaPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(ComaPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public ComaPower(AbstractCreature owner, int amount) {

        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
            addToTop(new ExhaustSpecificCardAction(c, AbstractDungeon.player.hand));
        }

}
