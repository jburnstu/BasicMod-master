package sleepermod.cards.attacks;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.IncreaseCostAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// Deal X damage. Increase the cost of this card by 1 this combat.
public class TossAndTurn extends AbstractSleeperCard {
    public static final String ID = makeID(TossAndTurn.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            AbstractCard.CardType.ATTACK, 
            AbstractCard.CardRarity.BASIC, 
            AbstractCard.CardTarget.ENEMY, 
            1 
    );

    private static final int DAMAGE = 11;
    private static final int UPG_DAMAGE = 3;
    public static final int magicNumber = 1;


    public TossAndTurn() {
        super(ID, info); 
        setDamage(DAMAGE, UPG_DAMAGE); 
        setMagic(magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        addToBot(new IncreaseCostAction(this.uuid, this.magicNumber));
    }



}