package sleepermod.cardsDeprecated.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.EgoismPower;
import sleepermod.util.CardStats;

//When you play a card of cost 2+, draw one card
public class Egoism extends AbstractSleeperCard {
    public static final String ID = makeID(Egoism.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            2 
    );


    int baseMagicNumber = 1;

    public Egoism() {
        super(ID, info); 
        this.magicNumber = this.baseMagicNumber;
        setMagic(magicNumber);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction(p,p,new EgoismPower(p,this.magicNumber)));
    }

    public void upgrade() {
        if (!this.upgraded) {
            upgradeName();
            upgradeMagicNumber(1);
        }
    }

    public AbstractCard makeCopy() {
        return new Egoism();
    }
}