package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import com.megacrit.cardcrawl.vfx.combat.ThrowDaggerEffect;
import joeymod.cardBeta.attacks.Reckoning;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;
import joeymod.patches.AbstractCardBackForgottenCardPatch;

public class ReckoningAction extends AbstractGameAction {

    private DamageInfo info;

    public ReckoningAction(AbstractCreature target, DamageInfo info) {
        this.duration = Settings.ACTION_DUR_XFAST;
        this.info = info;
        this.actionType = AbstractGameAction.ActionType.BLOCK;
        this.target = target;
    }

    public void update() {
        for (AbstractCard c : AbstractDungeon.player.hand.group) {
            if (c instanceof ForgottenCard) {
                addToTop(new DamageAction(this.target, this.info, true));
                if (this.target != null && this.target.hb != null)
                    addToTop(new VFXAction(new ThrowDaggerEffect(this.target.hb.cX, this.target.hb.cY)));
            }
        }
        this.isDone = true;
    }

}