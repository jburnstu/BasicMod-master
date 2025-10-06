package sleepermod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
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
        AbstractCard cardToPlay = this.card.frontForgottenCard;
        this.source.forgottenPile.group.remove(cardToPlay);
        AbstractDungeon.player.limbo.group.add(card);
        cardToPlay.current_y = -200.0F * Settings.scale;
        cardToPlay.target_x = Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
        cardToPlay.target_y = Settings.HEIGHT / 2.0F;
        cardToPlay.targetAngle = 0.0F;
        cardToPlay.lighten(false);
        cardToPlay.drawScale = 0.12F;
        cardToPlay.targetDrawScale = 0.75F;
        cardToPlay.applyPowers();
        addToTop(new NewQueueCardAction(cardToPlay, this.target, true, true));
        addToTop(new UnlimboAction(cardToPlay));

        if (!Settings.FAST_MODE) {
            addToTop(new WaitAction(Settings.ACTION_DUR_MED));
        } else {
            addToTop(new WaitAction(Settings.ACTION_DUR_FASTER));
        }

        if (cardToPlay instanceof AbstractSleeperCard && this.target instanceof AbstractMonster) {
            ((AbstractSleeperCard) cardToPlay).triggerOnRemembered(this.source, (AbstractMonster) this.target, true);
        }

        this.source.cardsRememberedThisCombat.add(cardToPlay);
        addToTop(new ShowCardAndPoofAction(this.card));

        for (AbstractPower power : this.source.powers) {
            if (power instanceof AbstractSleeperPower) {
                ((AbstractSleeperPower) power).onRemember(cardToPlay,this.target);
            }
        }
        for (AbstractRelic relic : this.source.relics) {
            if (relic instanceof AbstractSleeperRelic) {
                ((AbstractSleeperRelic) relic).onRemember(cardToPlay);
            }
        }
    this.isDone = true;
    }
}
