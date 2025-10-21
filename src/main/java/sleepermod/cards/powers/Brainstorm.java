package sleepermod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.BrainstormPower;
import sleepermod.util.CardStats;

//Retain 1 forgotten card each turn.
public class Brainstorm extends AbstractSleeperCard {
    public static final String ID = makeID(Brainstorm.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR,
            CardType.POWER,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            2
    );


    int baseMagicNumber = 1;
    int magicUpgrade = 0;

    public Brainstorm() {
        super(ID, info); 
        setMagic(baseMagicNumber,magicUpgrade);
        setCostUpgrade(1);
        setInnate(false,true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new BrainstormPower(p,magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new Brainstorm();
    }
}