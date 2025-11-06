package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.powers.RecurringDreamPower;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Gain X block, copy the next card you remember this turn
public class RecurringDream extends AbstractSleeperCard {
    public static final String ID = makeID(RecurringDream.class.getSimpleName());
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

    public RecurringDream() {
        super(ID, info);
        setBlock(BLOCK);
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
            addToBot(new GainBlockAction(p,block));
            addToBot(new ApplyPowerAction(p,p, new RecurringDreamPower(p,magicNumber)));
        }
    }
