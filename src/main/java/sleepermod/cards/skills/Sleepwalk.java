package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.ForgetAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//X block. draw 1 card and forget one card.
public class Sleepwalk extends AbstractSleeperCard {
    public static final String ID = makeID(Sleepwalk.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.BASIC, 
            CardTarget.SELF, 
            2 
    );

    private static final int BLOCK = 8;
    private static final int UPG_BLOCK = 3;
    private int baseMagicNumber = 1;

    public Sleepwalk() {
        super(ID, info); 
        this.forget = true;
        setBlock(BLOCK,UPG_BLOCK);
        setMagic(baseMagicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        addToBot(new DrawCardAction(magicNumber));
        addToBot(new ForgetAction(magicNumber,false,false,false));
        }
    }
