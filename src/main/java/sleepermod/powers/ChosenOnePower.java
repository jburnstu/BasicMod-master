package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static sleepermod.SleeperMod.makeID;


// Whenever you draw a forgotten card, gain 1 vigour.
public class ChosenOnePower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(ChosenOnePower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.DEBUFF;
    private static final boolean TURN_BASED = false;

    public AbstractPlayer p = AbstractDungeon.player;

    public ChosenOnePower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            flash();
            addToTop(new ApplyPowerAction(p, p, new VisionPower(p, 1)));
        }
        return damageAmount;
    }
}