package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.TheatreOfTheMindPower;
import sleepermod.util.CardStats;

//Gain 3 block and draw one card for every forgotten card in your hand. exhaust.
public class TheatreOfTheMind extends AbstractSleeperCard {
    public static final String ID = makeID(TheatreOfTheMind.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.SELF, 
            1 
    );

//    private static final int BLOCK = 4;
//    private static final int UPG_BLOCK = 1;
    private static final int baseMagicNumber = 1;

    public TheatreOfTheMind() {
        super(ID, info); 
//        setBlock(BLOCK, UPG_BLOCK); 
//        setExhaust(true,false);
        this.urgent = true;
        setMagic(baseMagicNumber);
        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p,p,new TheatreOfTheMindPower(p,1)));
    }
}