package sleepermod.cards.attacks;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// Deal X damage. If played from forgotten, apply 6 health. Exhaust.
public class Closure extends AbstractSleeperCard {
    public static final String ID = makeID(Closure.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.RARE, 
            CardTarget.ENEMY, 
            1 
    );

    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;
    public static final int baseMagicNumber = 4;
    public static final int upgradeMagic = 2;


    public Closure() {
        super(ID, info); 
        setDamage(DAMAGE, UPG_DAMAGE); 
        this.exhaust = true;
        this.magicNumber = baseMagicNumber;
        setMagic(magicNumber,upgradeMagic);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public void triggerOnRemembered(AbstractPlayer p, AbstractMonster m, boolean randomTarget) {
        addToBot(new HealAction(p, p, this.magicNumber));
    }

    @Override
    public void changeForgetForUpgrade() {
        this.forget = true;
    }
}