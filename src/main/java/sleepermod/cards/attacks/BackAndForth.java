package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

public class BackAndForth extends AbstractSleeperCard {
    public static final String ID = makeID(BackAndForth.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR,
            CardType.ATTACK,
            CardRarity.UNCOMMON,
            CardTarget.ALL_ENEMY,
            2 
    );

    private static final int baseMagicNumber = 2;
    private static final int DAMAGE = 10;
    private static final int UPG_DAMAGE = 4;

    public BackAndForth() {
        super(ID, info); 
        setDamage(DAMAGE, UPG_DAMAGE); 
        this.forget = true;
        this.magicNumber = baseMagicNumber;
        setMagic(magicNumber);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < this.magicNumber; i++) {
            addToTop(new AttackDamageRandomEnemyAction(this));
        }
    }


    public AbstractCard makeCopy() {
        return new BackAndForth();
    }
}
