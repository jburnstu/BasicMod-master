package joeymod.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;
import joeymod.cards.ForgottenCard;

import static joeymod.JoeyBasicMod.makeID;


// Whenever you draw a forgotten card, gain 1 vigour.
public class WoozyPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(WoozyPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;
    public boolean reduceAtEnd = true;

    public WoozyPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
        this.reduceAtEndOfTurn = reduceAtEnd;
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        if (c instanceof ForgottenCard) {
            flash();
            addToBot(new LoseHPAction(this.owner,null,this.amount, AbstractGameAction.AttackEffect.FIRE));
            }
        }



}