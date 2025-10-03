package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.cards.ForgottenCard;
import sleepermod.cards.skills.Slumber;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.AbstractSleeperPower;
import sleepermod.relics.AbstractSleeperRelic;

import java.util.ArrayList;

public class RememberAction extends AbstractGameAction {

    ForgottenCard card;
    MySleeperPlayer source;

    public RememberAction(AbstractMonster target, MySleeperPlayer source, ForgottenCard forgottenCardToRemember) {

        this.target = target;
        this.source = source;
        this.card = forgottenCardToRemember;
        this.actionType = ActionType.SPECIAL;
    }

    @Override
    public void update() {
        this.source.forgottenPile.group.remove(this.card.frontForgottenCard);
        switch (this.card.frontForgottenCard.target) {
            case ENEMY:
                addToTop(new NewQueueCardAction(this.card.frontForgottenCard, this.target, true, true));
                break;
            case SELF:
                addToTop(new NewQueueCardAction(this.card.frontForgottenCard, this.source, true, true));
                break;
            default:
                addToTop(new NewQueueCardAction(this.card.frontForgottenCard, this.target, true, true));
        }

        if (this.card.frontForgottenCard.target == AbstractCard.CardTarget.ENEMY) {
            addToTop(new NewQueueCardAction(this.card.frontForgottenCard, this.target, true, true));
        } else {
            addToTop(new NewQueueCardAction(this.card.frontForgottenCard, false, true, true));
        }
        if (this.card.frontForgottenCard instanceof AbstractSleeperCard && this.target instanceof AbstractMonster) {
            ((AbstractSleeperCard) this.card.frontForgottenCard).triggerOnRemembered(this.source, (AbstractMonster) this.target, true);
        }
        this.source.cardsRememberedThisCombat.add(this.card.frontForgottenCard);
        addToTop(new ShowCardAndPoofAction(this.card));
        for (AbstractPower power : this.source.powers) {
            if (power instanceof AbstractSleeperPower) {
                ((AbstractSleeperPower) power).onRemember(this.card.frontForgottenCard,this.target);
            }
        }
        for (AbstractRelic relic : this.source.relics) {
            if (relic instanceof AbstractSleeperRelic) {
                ((AbstractSleeperRelic) relic).onRemember(this.card.frontForgottenCard);
            }
        }
    this.isDone = true;
    }
}
