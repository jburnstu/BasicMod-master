package sleepermod.cardsBeta.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.BlankSlateAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.util.CardStats;

//Gain X block. Shuffle every forgotten card in play into your deck. exhaust.
public class BlankSlateColourlessRetain extends AbstractSleeperCard {
    public static final String ID = makeID(BlankSlateColourlessRetain.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            CardColor.COLORLESS, 
            CardType.SKILL, 
            CardRarity.SPECIAL, 
            CardTarget.SELF, 
            0 
    );

    public int BLOCK = 4;
    public int UPG_BLOCK = 2;
    public boolean baseExhaust = true;
    public boolean upgradeExhaust = false;

    public BlankSlateColourlessRetain() {
        super(ID, info); 
//        setExhaust(baseExhaust,upgradeExhaust);
//        setBlock(BLOCK,UPG_BLOCK);
        this.retain = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
//        addToBot(new GainBlockAction(p,block));
        addToTop(new BlankSlateAction());
        }
    }
