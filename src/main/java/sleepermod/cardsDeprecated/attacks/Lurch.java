package sleepermod.cardsDeprecated.attacks;

import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.ForgetTopCardAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//10 Damage. Forget a card.
public class Lurch extends AbstractSleeperCard {
    public static final String ID = makeID(Lurch.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, 
            CardType.ATTACK, 
            CardRarity.COMMON, 
            CardTarget.ENEMY, 
            1 
    );

    private static final int DMG = 8;
    private static final int UPG_DMG = 4;

    public Lurch() {
        super(ID, info); 

        setDamage(DMG, UPG_DMG); 
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL)));
        addToBot(new ForgetTopCardAction(null,false));
        }
    }
