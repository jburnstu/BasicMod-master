package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.EnterDreamsAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

// Deal X damage and draw 1 card for every woozy on the target. Exhaust
public class EnterDreams extends AbstractSleeperCard {
    public static final String ID = makeID(EnterDreams.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.UNCOMMON,
            CardTarget.ENEMY, 
            1 
    );

    private static final int DAMAGE = 4;
    private static final int UPG_DAMAGE = 2;
    private static int baseMagicNumber = 1;

    public EnterDreams() {
        super(ID, info); 
        this.exhaust = true;
        setDamage(DAMAGE, UPG_DAMAGE); 
        setMagic(baseMagicNumber);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EnterDreamsAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
          }
}

