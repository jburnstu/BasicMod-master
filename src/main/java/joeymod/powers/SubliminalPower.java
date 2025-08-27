package joeymod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;
// Whenever you remember a skill, apply 1 weak to all enemies.
public class SubliminalPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(SubliminalPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public SubliminalPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof ForgottenCard && ((ForgottenCard) c).frontForgottenCard.type == AbstractCard.CardType.SKILL) {
            flash();
            addToBot(new ApplyPowerAction(m, AbstractDungeon.player, new WoozyPower(m, 1)));
        }
    }

}