package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.InsomniaPower;
import sleepermod.util.CardStats;

// Deal 25 (30) damage. At the start of its turn, the target gains 20HP.
public class Doom extends AbstractSleeperCard {
    public static final String ID = makeID(Doom.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.COMMON, 
            CardTarget.ENEMY, 
            2 
    );

    private final static int DAMAGE = 10;
    private final static int UPG_DAMAGE = 0;
    private final static int baseMagicNumber = 3;
    private final static int magicUpgrade = 1;

    public Doom() {
        super(ID, info); 
//        this.forget = true;
        setDamage(DAMAGE,UPG_DAMAGE);
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m,new DamageInfo(p,DAMAGE)));
         addToBot(new ApplyPowerAction(m,p,new InsomniaPower(m,magicNumber)));
          }
}

