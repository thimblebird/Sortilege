package net.lyof.sortilege.enchants.staff;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

import java.util.function.BiConsumer;
import java.util.function.Predicate;

public class StaffEnchantment extends Enchantment {
    public int maxLevel;
    public BiConsumer<LivingEntity, Integer> effect;
    public Predicate<Enchantment> condition;

    public StaffEnchantment(Rarity pRarity, int maxLevel) {
        this(pRarity, maxLevel, null, null);
    }

    public StaffEnchantment(Rarity pRarity, int maxLevel,
                            BiConsumer<LivingEntity, Integer> effect, Predicate<Enchantment> condition) {
        super(pRarity, EnchantmentTarget.VANISHABLE, EquipmentSlot.values());
        this.maxLevel = maxLevel;
        this.effect = effect;
        this.condition = condition;
    }

    @Override
    protected boolean canAccept(Enchantment candidate) {
        return this.condition == null || this.condition.test(candidate);
    }

    public void triggerAttack(LivingEntity target, int level) {
        if (this.effect != null)
            this.effect.accept(target, level);
    }

    @Override
    public int getMaxLevel() {
        return this.maxLevel;
    }
}
