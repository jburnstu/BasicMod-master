package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.actions.BlankSlateAction;
import sleepermod.util.CardStats;

//Gain X block. Shuffle every forgotten card in play into your deck. exhaust.
public class BlankSlate extends AbstractSleeperCard {
    public static final String ID = makeID(BlankSlate.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.COMMON, 
            CardTarget.SELF, 
            0 
    );

    public int BLOCK = 4;
    public int UPG_BLOCK = 2;
    public boolean baseExhaust = true;
    public boolean upgradeExhaust = false;

    public BlankSlate() {
        super(ID, info); 
        setExhaust(baseExhaust,upgradeExhaust);
        setBlock(BLOCK,UPG_BLOCK);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        addToTop(new BlankSlateAction());
        }
    }
