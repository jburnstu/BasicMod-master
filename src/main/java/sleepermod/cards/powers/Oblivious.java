package sleepermod.cards.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.ObliviousPower;
import sleepermod.util.CardStats;

//When you play a card of cost 2+, draw one card
public class Oblivious extends AbstractSleeperCard {
    public static final String ID = makeID(Oblivious.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            1 
    );


    int baseMagicNumber = 2;
    int magicUpgrade = 1;

    public Oblivious() {
        super(ID, info); 
        setMagic(baseMagicNumber,magicUpgrade);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot((AbstractGameAction)new ApplyPowerAction(p,p,new ObliviousPower(p,magicNumber)));
    }


    public AbstractCard makeCopy() {
        return new Oblivious();
    }
}