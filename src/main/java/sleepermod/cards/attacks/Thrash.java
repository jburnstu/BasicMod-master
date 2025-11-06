package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// Attack 2x4 times. Forget.
public class Thrash extends AbstractSleeperCard {
    public static final String ID = makeID(Thrash.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.COMMON, 
            CardTarget.ENEMY, 
            1 
    );

    private static final int DAMAGE = 2;
    private static final int UPG_DAMAGE = 0;
    private static final int baseMagicNumber = 4;
    private static final int magicUpgrade = 1;

    public Thrash() {
        super(ID, info); 
        setDamage(DAMAGE, UPG_DAMAGE); 
        this.forget = true;
        setMagic(baseMagicNumber, magicUpgrade);

    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < magicNumber; i++) {
            addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        }
    }
}

