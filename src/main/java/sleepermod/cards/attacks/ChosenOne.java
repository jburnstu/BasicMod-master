package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.powers.ChosenOnePower;
import sleepermod.util.CardStats;

//X damage. Apply 2 woozy.
public class ChosenOne extends AbstractSleeperCard {
    public static final String ID = makeID(ChosenOne.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.RARE, 
            CardTarget.ENEMY, 
            2 
    );

    private static final int DMG = 5;
    private static final int UPG_DMG = 8;
    private static int baseMagicNumber = 1;
    private static int magicUpgrade = 0;


    public ChosenOne() {
        super(ID, info);
        setDamage(DMG, UPG_DMG); 
        setMagic(baseMagicNumber,magicUpgrade);
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new ApplyPowerAction(m,p,new ChosenOnePower(m,p,magicNumber)));
        };
    }
