package joeymod.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.util.CardStats;

public abstract class AbstractSleeperCard extends BaseCard {


    public AbstractSleeperCard(String ID, CardStats info) {
        super(ID, info);
    }

    public AbstractSleeperCard(String ID, CardStats info, String cardImage) {
        super(ID, info, cardImage);
    }

    public AbstractSleeperCard(String ID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color) {
        super(ID, cost, cardType, target, rarity, color);
    }

    public AbstractSleeperCard(String ID, int cost, CardType cardType, CardTarget target, CardRarity rarity, CardColor color, String cardImage) {
        super(ID, cost, cardType, target, rarity, color, cardImage);
    }

    public boolean forget = false;
    public boolean urgent = false;
    public ForgottenCard backForgottenCard;

    public void triggerOnForgotten () {}

    public void triggerOnPlayedFromForgotten (AbstractPlayer p, AbstractMonster m, boolean randomTarget) {}

}
