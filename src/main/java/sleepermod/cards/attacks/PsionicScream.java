package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// urgent. deal X damage to all enemies.
public class PsionicScream extends AbstractSleeperCard {
    public static final String ID = makeID(PsionicScream.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.COMMON, 
            CardTarget.ALL_ENEMY, 
            1 
    );

    private static final int DAMAGE = 8;
    private static final int UPG_DAMAGE = 3;

    public PsionicScream() {
        super(ID, info); 
        setDamage(DAMAGE, UPG_DAMAGE); 
        this.isMultiDamage = true;
        this.urgent = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAllEnemiesAction(p, damage, DamageInfo.DamageType.NORMAL, AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }
}

