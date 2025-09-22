package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import sleepermod.cards.ForgottenCard;
import sleepermod.patches.coremechanics.FieldPatchAbstractCardBackForgottenCard;

public class DreamTravelerAction extends AbstractGameAction {
    public DamageInfo info;

    public AbstractCard c;

    public AbstractPlayer p = AbstractDungeon.player;

    public DreamTravelerAction(AbstractCreature target, DamageInfo info, AbstractCard cardToTransform) {
        this.info = info;
        setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_MED;
        this.c = cardToTransform;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED &&
                this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AttackEffect.NONE));
            this.target.damage(this.info);
            ForgottenCard looseForgottenCard = FieldPatchAbstractCardBackForgottenCard.backForgottenCard.get(this.c);
            p.masterDeck.group.remove(this.c);
            AbstractCard newCard = AbstractDungeon.returnTrulyRandomCard().makeCopy();
            looseForgottenCard.frontForgottenCard = newCard;
        }
    }
}