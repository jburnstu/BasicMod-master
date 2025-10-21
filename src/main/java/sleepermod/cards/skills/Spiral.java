package sleepermod.cards.skills;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.MultiplyPowerAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;
import sleepermod.powers.InsomniaPower;


public class Spiral extends AbstractSleeperCard {
    public static final String ID = makeID(Spiral.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.SKILL, 
            CardRarity.UNCOMMON, 
            CardTarget.ENEMY, 
            1 
    );

    private int baseMagicNumber = 1;
    private int magicUpgrade = 0;

    public Spiral() {
        super(ID, info);
//        this.urgent = true;
        setMagic(baseMagicNumber,magicUpgrade);
        setExhaust(true,false);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToTop(new MultiplyPowerAction(m,p,InsomniaPower.POWER_ID,magicNumber));
    }
}