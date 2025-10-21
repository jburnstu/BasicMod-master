package sleepermod.cards.powers;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.AtemporalityPower;
import sleepermod.util.CardStats;

// The first time you remember a card, draw X cards.
public class Atemporality extends AbstractSleeperCard {
    public static final String ID = makeID(Atemporality.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.POWER, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            1 
    );

    public int baseMagicNumber = 1;
    public int magicUpgrade = 0;

    public Atemporality() {
        super(ID, info); 
        setMagic(baseMagicNumber,magicUpgrade);
        setCostUpgrade(0);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AtemporalityPower(p,magicNumber)));
    }

    public AbstractCard makeCopy() {
        return new Atemporality();
    }
}