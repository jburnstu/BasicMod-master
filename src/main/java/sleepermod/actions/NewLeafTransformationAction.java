package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import sleepermod.cards.ForgottenCard;
import sleepermod.patches.coremechanics.FieldPatchAbstractCardBackForgottenCard;

public class NewLeafTransformationAction extends AbstractGameAction {
    public AbstractPlayer p = AbstractDungeon.player;

    public AbstractCard newCard = null;

    public NewLeafTransformationAction() {
        this.duration = 0.0F;
        this.actionType = ActionType.WAIT;
    }


    public void update () {
        AbstractCard c = AwakenAction.awakenedCards.get(0);
        AbstractCard cardInMasterDeck = null;
        for (AbstractCard masterCard : p.masterDeck.group) {
            if (masterCard.uuid == c.uuid) {
                cardInMasterDeck = masterCard;
                break;
            }
        }
        ForgottenCard looseForgottenCard = FieldPatchAbstractCardBackForgottenCard.backForgottenCard.get(c);
        p.masterDeck.group.remove(cardInMasterDeck);
        p.hand.group.remove(c);
        this.newCard = AbstractDungeon.returnTrulyRandomCard().makeCopy();
        p.masterDeck.group.add(this.newCard);
        p.hand.group.add(this.newCard);
        looseForgottenCard.frontForgottenCard = this.newCard;
        if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
        if (this.newCard != null) {
            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(this.newCard.makeStatEquivalentCopy()));
            addToTop((AbstractGameAction)new WaitAction(Settings.ACTION_DUR_MED));
        }
        this.isDone = true;
    }
}
