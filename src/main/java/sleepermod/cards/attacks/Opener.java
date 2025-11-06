package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.OpenerAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//Urgent. x damage. if first card played, damage again
public class Opener extends AbstractSleeperCard {
    public static final String ID = makeID(Opener.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DMG = 8;
    private static final int UPG_DMG = 3;
    private static int baseMagicNumber = 1;
//    private static int magicUpgrade = 1;


    public Opener() {
        super(ID, info); 
        setDamage(DMG, UPG_DMG); 
        setMagic(baseMagicNumber);
        this.urgent = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new OpenerAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        };
    }
