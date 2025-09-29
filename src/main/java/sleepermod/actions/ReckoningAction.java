package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import sleepermod.cards.ForgottenCard;
import sleepermod.powers.InsomniaPower;

public class ReckoningAction extends AbstractGameAction {

    private DamageInfo info;
    private int insomniaAmount;

    public ReckoningAction(AbstractCreature target, DamageInfo info, int insomniaAmount) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.info = info;
        this.actionType = AbstractGameAction.ActionType.BLOCK;
        this.target = target;
        this.insomniaAmount = insomniaAmount;
    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ForgottenCard) {
                addToTop(new DamageAction(this.target, this.info, true));
                addToTop(new ApplyPowerAction(target, AbstractDungeon.player, new InsomniaPower(target,this.insomniaAmount)));
                if (this.target != null && this.target.hb != null)
                    addToTop(new VFXAction(new ThrowDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
            }
        }
        this.isDone = true;
    }
}