package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.LightSleeperAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//   X block. draw cards and discard (forget?) non forgotten
public class LightSleeper extends AbstractSleeperCard {
    public static final String ID = makeID(LightSleeper.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.SELF, 
            1 
    );

    private static final int BLOCK = 7;
    private static final int UPG_BLOCK = 3;
    private static final int baseMagicNumber = 4;

    public LightSleeper() {
        super(ID, info); 
        setBlock(BLOCK, UPG_BLOCK); 
        setMagic(baseMagicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        addToBot(new DrawCardAction(magicNumber, new LightSleeperAction()));
    }
}

