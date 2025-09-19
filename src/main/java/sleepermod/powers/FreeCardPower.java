package sleepermod.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FreeCardPower extends AbstractPower {
    AbstractPlayer owner;
    int amount;

    public FreeCardPower(AbstractPlayer p, int amount) {
        this.owner = p;
        this.amount = amount;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && this.amount > 0) {
            flash();
            this.amount--;
            if (this.amount == 0) {
                addToTop(new RemoveSpecificPowerAction(this.owner, this.owner, "FreeCardPower"));
            }
        }
    }
}