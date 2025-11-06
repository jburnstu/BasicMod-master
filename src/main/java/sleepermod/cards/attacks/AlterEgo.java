package sleepermod.cards.attacks;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.AlterEgoAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;


public class AlterEgo extends AbstractSleeperCard {
    public static final String ID = makeID(AlterEgo.class.getSimpleName());
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.UNCOMMON, 
            CardTarget.ENEMY, 
            1 
    );

    private static final int DMG = 10;
    private static final int UPG_DAMAGE = 4;

    public AlterEgo() {
        super(ID, info); 
        setDamage(DMG, UPG_DAMAGE); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new AlterEgoAction());
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        }

}