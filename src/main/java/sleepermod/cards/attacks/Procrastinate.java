package sleepermod.cards.attacks;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// Deal X damage. Increase the cost of this card by 1 this combat.
public class Procrastinate extends AbstractSleeperCard {
    public static final String ID = makeID(Procrastinate.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.UNCOMMON, 
            CardTarget.ENEMY, 
            2 
    );

    private static final int DAMAGE = 13;
    private static final int UPG_DAMAGE = 0;
    public static final int baseMagicNumber = 5;


    public Procrastinate() {
        super(ID, info); 
        setDamage(DAMAGE, UPG_DAMAGE); 
        setMagic(baseMagicNumber);
        this.forget = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new ModifyDamageAction(this.uuid, magicNumber));
    }

    @Override
    public void triggerOnForgotten() {
    }

//    @Override
//    public void changeForgetForUpgrade() {
//        this.forget = true;
//    }

}