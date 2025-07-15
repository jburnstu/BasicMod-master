package joeymod.powers;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;

public class FugueStatePowerButMakeARelic extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(FugueStatePowerButMakeARelic.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;

// Whenever you remember a power card, add a copy to your deck.
    public FugueStatePowerButMakeARelic(AbstractCreature owner, int amount) {

        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof ForgottenCard && ((ForgottenCard) c).frontForgottenCard.type == AbstractCard.CardType.POWER) {
           AbstractCard copy = new ((ForgottenCard) c).frontForgottenCard.getClass();        }

    }

}