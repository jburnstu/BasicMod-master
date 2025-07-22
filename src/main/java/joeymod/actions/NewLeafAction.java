package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.UpgradeShineEffect;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardBrieflyEffect;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;
import joeymod.cards.AbstractSleeperCard;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;

import java.util.ArrayList;

public class NewLeafAction extends AbstractGameAction {
    private DamageInfo info;

    private AbstractCard theCard = null;

    public NewLeafAction(AbstractCreature target, DamageInfo info) {
        this.info = info;
        setValues(target, info);
        this.actionType = AbstractGameAction.ActionType.DAMAGE;
        this.duration = Settings.ACTION_DUR_MED;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_MED &&
                this.target != null) {
            AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, AbstractGameAction.AttackEffect.NONE));
            this.target.damage(this.info);
            if ((((AbstractMonster) this.target).isDying || this.target.currentHealth <= 0) && !this.target.halfDead &&
                    !this.target.hasPower("Minion")) {
                ArrayList<AbstractCard> possibleCards = new ArrayList<>();
                for (AbstractCard c : AbstractDungeon.player.masterDeck.group) {
                    if (((MySleeperPlayer) AbstractDungeon.player).forgottenPile.group.contains(c)) {
                        possibleCards.add(c);
                    }
                }
                if (possibleCards.isEmpty()) {
                    this.isDone = true;
                    return;
                }
                if (possibleCards.size() == 1) {
                    AbstractCard c = possibleCards.get(0);
                    ForgottenCard looseForgottenCard = ((AbstractSleeperCard) c).backForgottenCard;
                    AbstractDungeon.player.masterDeck.group.remove(c);
                    AbstractCard newCard = AbstractDungeon.returnTrulyRandomCard().makeCopy();
                    looseForgottenCard.frontForgottenCard = newCard;
                    return;
                }
            }
        }
    }
}
//                        numForgotten = this.amount;
//                        AbstractDungeon.gridSelectScreen.open(forgettable, numForgotten, "Choose a card(s) to forget",this.anyNumber, this.canPickZero);
//                        tickDuration();
//                        return;
//                    }
//                    if (AbstractDungeon.gridSelectScreen.selectedCards.size() != 0) {
//                        for (AbstractCard c : AbstractDungeon.gridSelectScreen.selectedCards) {
//                            c.unhover();
//                            newForgottenCard = Move.toForgottenPile(this.p.hand, c, true);
//                            this.p.hand.refreshHandLayout();
//                            this.p.hand.applyPowers();
//                        }
//                        AbstractDungeon.gridSelectScreen.selectedCards.clear();
//                        this.p.hand.refreshHandLayout();
//                    }
//                }
//            }
//            if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead())
//                AbstractDungeon.actionManager.clearPostCombatActions();
//        }
//        tickDuration();
//        if (this.isDone && this.theCard != null) {
//            AbstractDungeon.effectsQueue.add(new UpgradeShineEffect(Settings.WIDTH / 2.0F, Settings.HEIGHT / 2.0F));
//            AbstractDungeon.topLevelEffectsQueue.add(new ShowCardBrieflyEffect(this.theCard.makeStatEquivalentCopy()));
//            addToTop((AbstractGameAction)new WaitAction(Settings.ACTION_DUR_MED));