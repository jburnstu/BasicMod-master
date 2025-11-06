package sleepermod.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.AwakenAction;
import sleepermod.actions.EpiphanyAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//urgent. Recollect 1. exhaust.
public class Epiphany extends AbstractSleeperCard {
    public static final String ID = makeID(Epiphany.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            4 
    );

    private int baseMagicNumber = 1;
    private int playAmt = 2;

    public Epiphany() {
        super(ID, info);
        setMagic(baseMagicNumber);
        setCostUpgrade(3);
        this.exhaust = true;
//        setExhaust(true,false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AwakenAction(magicNumber, new EpiphanyAction(playAmt)));
    }
}