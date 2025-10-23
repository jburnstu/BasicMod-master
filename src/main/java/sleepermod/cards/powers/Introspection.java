package sleepermod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.IntrospectionPower;
import sleepermod.util.CardStats;

//Whenever you forget a card, gain 3 block.
public class Introspection extends AbstractSleeperCard {
    public static final String ID = makeID(Introspection.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            1 
    );

    public int baseMagicNumber = 3;
    public int magicUpgrade = 4;


    public Introspection() {
        super(ID, info); 
        setMagic(baseMagicNumber,magicUpgrade);
        setInnate(false,true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new IntrospectionPower(p,magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new Introspection();
    }
}