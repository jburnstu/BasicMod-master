package sleepermod.cardsBeta;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.FlashbackAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Gain X block, copy the next card you remember this turn
public class Flashback extends AbstractSleeperCard {
    public static final String ID = makeID(Flashback.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.SELF,
            1
    );

    private static final int BLOCK = 4;
    private static final int baseMagicNumber = 1;
    private static final int magicUpgrade = 1;

    public Flashback() {
        super(ID, info);
        setBlock(BLOCK);
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new FlashbackAction(1));
        }
    }
