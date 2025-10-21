package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//5 Block
public class SleeperDefend extends AbstractSleeperCard {
    public static final String ID = makeID(SleeperDefend.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.BASIC, 
            CardTarget.SELF, 
            1 
    );

    private static final int BLOCK = 5;
    private static final int UPG_BLOCK = 3;

    public SleeperDefend() {
        super(ID, info); 
        tags.add(CardTags.STARTER_DEFEND);
        setBlock(BLOCK, UPG_BLOCK); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }
}