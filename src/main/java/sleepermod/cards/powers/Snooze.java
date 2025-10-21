package sleepermod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.SnoozePower;
import sleepermod.util.CardStats;

//Retain 1 forgotten card each turn.
public class Snooze extends AbstractSleeperCard {
    public static final String ID = makeID(Snooze.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            1 
    );


    int baseMagicNumber = 2;
    int magicUpgrade = 0;

    public Snooze() {
        super(ID, info); 
        setMagic(baseMagicNumber,magicUpgrade);
        setCostUpgrade(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new SnoozePower(p,magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new Snooze();
    }
}