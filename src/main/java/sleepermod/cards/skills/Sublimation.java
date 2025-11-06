package sleepermod.cards.skills;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.powers.AmnesiaPower;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Draw three cards. gain 1 amnesia [exhaust the next forgotten card you play].
public class Sublimation extends AbstractSleeperCard {
    public static final String ID = makeID(Sublimation.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.COMMON, 
            CardTarget.SELF, 
            0 
    );

    private int baseMagicNumber = 2;
    private int magicUpgrade = 1;


    public Sublimation() {
        super(ID, info); 
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {

        addToBot(new DrawCardAction(p,magicNumber));
        addToBot(new ApplyPowerAction(p,p, new AmnesiaPower(p,1)));
    }
}