package sleepermod.cardsDeprecated.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.AllEnemiesLoseEqualToInsomniaAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;


public class LongNight extends AbstractSleeperCard {
    public static final String ID = makeID(LongNight.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.RARE, 
            CardTarget.ALL_ENEMY, 
            2 
    );

    private int baseMagicNumber = 1;
    private int magicUpgrade = 0;

    public LongNight() {
        super(ID, info); 
        setExhaust(true,false);
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new AllEnemiesLoseEqualToInsomniaAction());
    }
}