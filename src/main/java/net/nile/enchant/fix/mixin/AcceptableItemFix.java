package net.nile.enchant.fix.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ImpalingEnchantment;
import net.minecraft.enchantment.FireAspectEnchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.TridentItem;



@Mixin(Enchantment.class)
public class AcceptableItemFix {
    @Inject(method="isAcceptableItem(Lnet/minecraft/item/ItemStack;)Z", cancellable = true, at = @At("HEAD"))
    private void nileIsAcceptableItem(ItemStack stack, CallbackInfoReturnable<Boolean> cb)
    {
        Enchantment ench = (Enchantment)(Object)this;
        Item item = stack.getItem();
        if(ench instanceof ImpalingEnchantment)
        {
            if(item instanceof SwordItem || item instanceof AxeItem)
            {
                cb.setReturnValue(true);
            }
        }
        else if(ench instanceof FireAspectEnchantment)
        {
            if(item instanceof TridentItem || item instanceof AxeItem)
            {
                cb.setReturnValue(true);
            }
        }
    }
}
