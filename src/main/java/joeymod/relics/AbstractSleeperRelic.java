package joeymod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public abstract class AbstractSleeperRelic extends CustomRelic {
    public AbstractSleeperRelic(String id, AbstractRelic.RelicTier tier, AbstractRelic.LandingSound landingSound) {
            super(id, "", tier, landingSound);
//            this.img = ImageMaster.loadImage(MadScienceMod.relicImage(id));
//            this.largeImg = ImageMaster.loadImage(MadScienceMod.relicLargeImage(id));
//            this.outlineImg = ImageMaster.loadImage(MadScienceMod.relicOutlineImage(id));
        }


    public void onForget (AbstractCard card) {}
}