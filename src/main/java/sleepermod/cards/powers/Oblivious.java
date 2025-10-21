package sleepermod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.ObliviousPower;
import sleepermod.util.CardStats;

//Whenever you forget a card, gain 3 block.
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

    public int baseMagicNumber = 2;
    public int magicUpgrade = 0;


    public Oblivious() {
        super(ID, info); 
        setMagic(baseMagicNumber,magicUpgrade);
        setInnate(false,true);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new ObliviousPower(p,magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new Oblivious();
    }
}