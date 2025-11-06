package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.DeepestFearAction;
import sleepermod.actions.EnterDreamsAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// Deal X damage and draw 1 card for every woozy on the target. Exhaust
public class DeepestFear extends AbstractSleeperCard {
    public static final String ID = makeID(DeepestFear.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.RARE, 
            CardTarget.ALL_ENEMY, 
            2 
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    private static int baseMagicNumber = 1;

    public DeepestFear() {
        super(ID, info); 
        this.exhaust = true;
        setDamage(DAMAGE, UPG_DAMAGE); 
        setMagic(baseMagicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (AbstractMonster mo : AbstractDungeon.getCurrRoom().monsters.monsters) {
            addToTop(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
            addToTop(new DeepestFearAction(m,magicNumber));
        }
    }
}

