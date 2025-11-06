package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.InsomniaPower;
import sleepermod.util.CardStats;

//Forgotten cards cost zero this turn. This turn, when you draw a draw a non-forgotten card, discard it.
public class SleepNoMore extends AbstractSleeperCard {
    public static final String ID = makeID(SleepNoMore.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.COMMON, 
            CardTarget.ENEMY, 
            1 
    );


    public int baseMagicNumber = 4;
    public int magicUpgrade = 2;

    public SleepNoMore() {
        super(ID, info); 
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(m,p,new InsomniaPower(m,magicNumber)));
    }
}