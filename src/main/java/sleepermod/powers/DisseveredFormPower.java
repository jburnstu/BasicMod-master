package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;
import sleepermod.cards.ForgottenCard;

import static sleepermod.SleeperMod.makeID;

// All forgotten cards become urgent. Whenenver you play a forgotten card, gain 1 energy next turn.
public class DisseveredFormPower extends AbstractSleeperPower {
    public static final String POWER_ID = makeID(DisseveredFormPower.class.getSimpleName());
    private static final PowerType TYPE = PowerType.BUFF;
    private static final boolean TURN_BASED = false;


    public DisseveredFormPower(AbstractCreature owner, int amount) {
        super(POWER_ID, TYPE, false, owner, amount);
    }

    @Override
    public void onForget(AbstractCard card) {
        addToTop(new GainBlockAction(this.owner, this.amount));
    }

    @Override
    public void onCardDraw(AbstractCard c) {
        if (c instanceof ForgottenCard) {
            ((ForgottenCard) c).urgent = true;
            // change description
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
            if (c instanceof ForgottenCard) {
                addToTop(new ApplyPowerAction(AbstractDungeon.player,AbstractDungeon.player, new EnergizedPower(AbstractDungeon.player,1)));
        }
    }

}