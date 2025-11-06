package sleepermod.cardsDeprecated.attacks;


import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.AwakenAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// Deal X damage. If played forgotten, recollect 1.
public class TriggerPhrase extends AbstractSleeperCard {
    public static final String ID = makeID(TriggerPhrase.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.UNCOMMON, 
            CardTarget.ENEMY, 
            2 
    );

    private static final int DAMAGE = 12;
    private static final int UPG_DAMAGE = 4;
    public static final int magicNumber = 1;


    public TriggerPhrase() {
        super(ID, info); 
        setDamage(DAMAGE, UPG_DAMAGE); 
        setMagic(magicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
    }

    @Override
    public void triggerOnRemembered(AbstractPlayer p, AbstractMonster m, boolean randomTarget) {
        addToBot(new AwakenAction(1,false));
    }


}