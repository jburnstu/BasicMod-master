package sleepermod.cards;

import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAndPoofAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sleepermod.actions.RememberAction;
import sleepermod.character.MySleeperPlayer;
import sleepermod.patches.coremechanics.FieldPatchAbstractCardBackForgottenCard;
import sleepermod.powers.TrancePower;
import sleepermod.util.CardStats;

public class ForgottenCard extends AbstractSleeperCard {
    public static final String ID = makeID(ForgottenCard.class.getSimpleName());
    private static Object MyCharacter;
    private static final CardStats info = new CardStats(
            MySleeperPlayer.Meta.CARD_COLOR, //The card color. If you're making your own character, it'll look something like this. Otherwise, it'll be CardColor.RED or similar for a basegame character color.
            CardType.STATUS, //The type. ATTACK/SKILL/POWER/CURSE/STATUS
            CardRarity.SPECIAL, //Rarity. BASIC is for starting cards, then there's COMMON/UNCOMMON/RARE, and then SPECIAL and CURSE. SPECIAL is for cards you only get from events. Curse is for curses, except for special curses like Curse of the Bell and Necronomicurse.
            CardTarget.ENEMY, //The target. Single target is ENEMY, all enemies is ALL_ENEMY. Look at cards similar to what you want to see what to use.
            1 //The card's base cost. -1 is X cost, -2 is no cost for unplayable cards like curses, or Reflex.
    );
    //These will be used in the constructor. Technically you can just use the values directly,
    //but constants at the top of the file are easy to adjust.
    public AbstractCard frontForgottenCard;
    MySleeperPlayer p;

    public ForgottenCard(AbstractCard c) {
        super(ID, info); //Pass the required information to the BaseCard constructor.
        this.frontForgottenCard = c;
        FieldPatchAbstractCardBackForgottenCard.backForgottenCard.set(c,this);
        this.p = (MySleeperPlayer) AbstractDungeon.player;
        this.purgeOnUse = true;

    }

    public ForgottenCard() {
        super(ID, info);
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (AbstractDungeon.player.hasPower(TrancePower.POWER_ID)) {
            this.rawDescription = cardStrings.EXTENDED_DESCRIPTION[0] + cardStrings.DESCRIPTION;
            this.urgent = true;
            this.setCostForTurn(0);
        } else {
            this.rawDescription = cardStrings.DESCRIPTION;
            this.urgent = false;
            this.setCostForTurn(this.cost);
        }
        initializeDescription();
    }


    public void onMoveToDiscard() {
        this.rawDescription = cardStrings.DESCRIPTION;
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RememberAction(m,(MySleeperPlayer) p,this));
    }



    public void upgrade () {}
}