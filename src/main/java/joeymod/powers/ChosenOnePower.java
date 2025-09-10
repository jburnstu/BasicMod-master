package joeymod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;


// Whenever you draw a forgotten card, gain 1 vigour.
public class ChosenOnePower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(ChosenOnePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public AbstractPlayer p = AbstractDungeon.player;

    public ChosenOnePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card instanceof ForgottenCard && m.equals(this.owner)) {
            addToBot(new ApplyPowerAction(p,p,new VisionPower(p,1)));
        }
    }

}