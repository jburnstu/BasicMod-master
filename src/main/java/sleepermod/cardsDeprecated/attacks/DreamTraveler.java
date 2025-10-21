package sleepermod.cardsDeprecated.attacks;

import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.DreamTravelerAction;
import sleepermod.cards.AbstractSleeperCard;
import sleepermod.character.MySleeperPlayer;
import sleepermod.util.CardStats;

//X damage then transform
public class DreamTraveler extends AbstractSleeperCard {
    public static final String ID = makeID(DreamTraveler.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR,
            CardType.SKILL,
            CardRarity.UNCOMMON,
            CardTarget.ENEMY, 
            2 
    );

    private static final int DMG = 18;
    private static final int UPG_DMG = 6;

    public DreamTraveler() {
        super(ID, info); 
        setDamage(DMG,UPG_DMG);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DreamTravelerAction(m,new DamageInfo(p, damage, DamageInfo.DamageType.NORMAL),this));
        }
    }
