package joeymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.GetAllInBattleInstances;
import com.megacrit.cardcrawl.localization.UIStrings;
import joeymod.cards.ForgottenCard;
import joeymod.character.MySleeperPlayer;

import java.util.UUID;

public class IncreaseCostAction extends AbstractGameAction {
    UUID uuid;

    private AbstractCard card = null;

    public IncreaseCostAction(AbstractCard card) {
        this.card = card;
    }

    public IncreaseCostAction(UUID targetUUID, int amount) {
        this.uuid = targetUUID;
        this.amount = amount;
        this.duration = Settings.ACTION_DUR_XFAST;
    }

    public void update() {
        if (this.card == null) {
            for (AbstractCard c : GetAllInBattleInstances.get(this.uuid))
                c.modifyCostForCombat(1);
        } else {
            this.card.modifyCostForCombat(1);
        }
        this.isDone = true;
    }
}