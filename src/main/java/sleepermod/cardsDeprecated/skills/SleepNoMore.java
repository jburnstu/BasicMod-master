package sleepermod.cardsDeprecated.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.SleepNoMoreAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// Deal 25 (30) damage. At the start of its turn, the target gains 20HP.
public class SleepNoMore extends AbstractSleeperCard {
    public static final String ID = makeID(SleepNoMore.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.RARE,
            CardTarget.ENEMY,
            2
    );

    private static int baseMagicNumber = 2;
    private static int magicUpgrade = 1;

    public SleepNoMore() {
        super(ID, info);
        this.exhaust = true;
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
         addToBot(new SleepNoMoreAction(m,magicNumber));

          }
}

