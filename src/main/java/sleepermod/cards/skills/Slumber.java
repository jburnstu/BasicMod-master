package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.GainEnergyIfForgetAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//  X block. If forgot a card this turn, gain the energy back
public class Slumber extends AbstractSleeperCard {
    public static final String ID = makeID(Slumber.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.COMMON, 
            CardTarget.SELF, 
            2 
    );

    private static final int BLOCK = 10;
    private static final int UPG_BLOCK = 4;
    private static final int baseMagicNumber = 2;

    public static int totalForgottenThisTurn = 0;

    public Slumber() {
        super(ID, info); 
        setBlock(BLOCK, UPG_BLOCK); 
        setMagic(baseMagicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p,block));
        addToBot(new GainEnergyIfForgetAction(magicNumber, (Slumber.totalForgottenThisTurn != 0)));
    }

    @Override
    public void atTurnStartPreDraw() {
        Slumber.totalForgottenThisTurn = 0;
    }

}

