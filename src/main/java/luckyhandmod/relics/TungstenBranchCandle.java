package luckyhandmod.relics;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import luckyhandmod.LuckyHandMod;

import java.util.Collections;

import static luckyhandmod.LuckyHandMod.makeID;

public class TungstenBranchCandle extends BaseRelic {
    private static final String NAME = "TungstenBranchCandle"; //The name will be used for determining the image file as well as the ID.
    public static final String ID = makeID(NAME); //This adds the mod's prefix to the relic ID, resulting in modID:MyRelic
    private static final RelicTier RARITY = RelicTier.SPECIAL; //The relic's rarity.
    private static final LandingSound SOUND = LandingSound.MAGICAL; //The sound played when the relic is clicked.
    private static int ExtraEnergy;
    private static int ExtraCard;

    public TungstenBranchCandle() {
        super(ID, NAME, RARITY, SOUND);
        ExtraEnergy = LuckyHandMod.config.getInt("Lucky_Hand_ExtraEnergy");
        ExtraCard = LuckyHandMod.config.getInt("Lucky_Hand_ExtraCard");
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.type == AbstractCard.CardType.CURSE) {
            flash();
            card.exhaust = true;
            action.exhaustCard = true;
            addToBot(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            addToBot(new MakeTempCardInHandAction(AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy(), false));
        }
    }

    @Override
    public void onEquip() {
        AbstractDungeon.player.masterHandSize += ExtraCard;
        AbstractDungeon.player.energy.energyMaster += ExtraEnergy;
    }

    @Override
    public void onUnequip() {
        AbstractDungeon.player.masterHandSize -= ExtraCard;
        AbstractDungeon.player.energy.energyMaster -= ExtraEnergy;
    }

    @Override
    public String getUpdatedDescription() {
        return this.DESCRIPTIONS[0] + LuckyHandMod.config.getInt("Lucky_Hand_ExtraCard") + this.DESCRIPTIONS[1] + String.join("", Collections.nCopies(LuckyHandMod.config.getInt("Lucky_Hand_ExtraEnergy"), "[E] ")) + this.DESCRIPTIONS[2];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new TungstenBranchCandle();
    }

    @Override
    public int changeNumberOfCardsInReward(int numberOfCards) {
        return 0;
    }
}
