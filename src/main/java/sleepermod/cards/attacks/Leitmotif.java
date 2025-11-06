package sleepermod.cards.attacks;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.IncreaseCostAction;
import sleepermod.actions.AwakenSpecificCardAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// Deal X damage. Whenever you recollect, also recollect this card (if it is forgotten). forget.
public class Leitmotif extends AbstractSleeperCard {
    public static final String ID = makeID(Leitmotif.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.UNCOMMON, 
            CardTarget.ENEMY, 
            0 
    );

    private static final int DAMAGE = 5;
    private static final int UPG_DAMAGE = 3;
    public static final int magicNumber = 1;


    public Leitmotif() {
        super(ID, info); 
        setDamage(DAMAGE, UPG_DAMAGE); 
        this.forget = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    public void triggerOnCardAwoken() {
        System.out.println("Got to Leitmotif's tOCA");
        if (((MySleeperPlayer) AbstractDungeon.player).forgottenPile.group.contains(this)) {
            addToBot(new AwakenSpecificCardAction(this));
        }
    }

}