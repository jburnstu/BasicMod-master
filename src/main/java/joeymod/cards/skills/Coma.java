package joeymod.cards.skills;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import joeymod.actions.FreeForgottenCardsAction;
import joeymod.cards.AbstractSleeperCard;
import joeymod.character.MySleeperPlayer;
import joeymod.patches.AbstractCardBackForgottenCardPatch;
import joeymod.powers.ComaPower;
import joeymod.util.CardStats;

//Forgotten cards cost zero this turn. This turn, when you draw a draw a non-forgotten card, discard it.
public class Coma extends AbstractSleeperCard {
    public static final String ID = makeID(Coma.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.SKILL, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.RARE, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            3 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.

    public Coma() {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new FreeForgottenCardsAction(p));
        addToBot(new ApplyPowerAction(p, p, new ComaPower(p, this.magicNumber)));

    }
}