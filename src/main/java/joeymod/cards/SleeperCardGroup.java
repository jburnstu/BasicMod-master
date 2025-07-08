package joeymod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import joeymod.character.MySleeperPlayer;


public class SleeperCardGroup extends CardGroup {

    public SleeperCardGroup(CardGroup.CardGroupType type) {
        super(type);
    }

    MySleeperPlayer p;

    public AbstractCard moveToForgottenPile(AbstractCard c) {
        this.removeCard(c);
        ForgottenCard newForgottenCard = new ForgottenCard(c);
        this.addToBottom(newForgottenCard);
        this.p.forgottenPile.addToTop(c);
        return newForgottenCard;
    }


}
