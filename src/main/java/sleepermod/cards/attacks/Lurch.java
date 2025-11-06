package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.AmnesiaPower;
import sleepermod.util.CardStats;

//X damage. Apply 2 woozy.
public class Lurch extends AbstractSleeperCard {
    public static final String ID = makeID(Lurch.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.UNCOMMON, 
            CardTarget.ENEMY, 
            0 
    );

    private static final int DMG = 7;
    private static final int UPG_DMG = 4;
    private static int baseMagicNumber = 1;
    private static int magicUpgrade = 0;


    public Lurch() {
        super(ID, info); 
        setDamage(DMG, UPG_DMG); 
        setMagic(baseMagicNumber,magicUpgrade);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new ApplyPowerAction(p,p,new AmnesiaPower(p,magicNumber)));
        };
    }
