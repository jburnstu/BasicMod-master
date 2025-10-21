package sleepermod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.PavlovianResponsePower;
import sleepermod.util.CardStats;

//Whenever you remember an attack, gain 1 strength.
public class PavlovianResponse extends AbstractSleeperCard {
    public static final String ID = makeID(PavlovianResponse.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            3 
    );

    public int baseMagicNumber = 1;

    public PavlovianResponse() {
        super(ID, info);
        setMagic(baseMagicNumber);
        setCostUpgrade(2);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new PavlovianResponsePower(p,magicNumber)));
    }


    public AbstractCard makeCopy() {
        return new PavlovianResponse();
    }
}