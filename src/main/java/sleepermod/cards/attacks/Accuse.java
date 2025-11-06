package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.TrancePower;
import sleepermod.util.CardStats;


public class Accuse extends AbstractSleeperCard {
    public static final String ID = makeID(Accuse.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.COMMON,
            CardTarget.ENEMY,
            1
    );

    private static final int DMG = 4;
    private static final int UPG_DMG = 2;
    private static final int baseMagicNumber = 1;
    private static final int magicUpgrade = 1;

    public Accuse() {
        super(ID, info);
//        this.urgent = true;
        setDamage(DMG, UPG_DMG); 
        setMagic(baseMagicNumber,magicUpgrade);
//        setCostUpgrade(0);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new ApplyPowerAction(m, p, new VulnerablePower(m,magicNumber,false)));
        addToBot(new ApplyPowerAction(p,p, new TrancePower(p,1)));
        };
    }
