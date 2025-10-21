package sleepermod.cardsDeprecated.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//6 block. if played from forgotten, gain an additional 6 block.
public class WakeFromDanger extends AbstractSleeperCard {
    public static final String ID = makeID(WakeFromDanger.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.COMMON, 
            CardTarget.SELF, 
            1 
    );

    private static final int BLOCK = 6;
    private static final int UPG_BLOCK = 3;

    public WakeFromDanger() {
        super(ID, info); 
        setBlock(BLOCK, UPG_BLOCK); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));
    }

    @Override
    public void triggerOnRemembered(AbstractPlayer p, AbstractMonster m, boolean randomTarget) {
    addToTop(new GainBlockAction(p, block));
    }
}