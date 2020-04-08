package net.seanomik.energeticstorage;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import net.seanomik.energeticstorage.utils.Utils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.UUID;

public enum Skulls {

    LeftGreenArrow("LeftGreenArrow", 0, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTRjNDE3NDZhZjU1N2EyNGJlOGEwOTAzNjlhNTkxYWU2M2Q1Y2U5YzRiZjQwNWQzNTQyNDdkODEwYzdjNyJ9fX0=", "5da5509d-136d-4716-bc2d-d04f82058e91"),
    UpGreenArrow("UpGreenArrow", 1, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjIyMWRhNDQxOGJkM2JmYjQyZWI2NGQyYWI0MjljNjFkZWNiOGY0YmY3ZDRjZmI3N2ExNjJiZTNkY2IwYjkyNyJ9fX0=", "fb271319-5c42-4f5d-9389-e89ca7caf96a"),
    Computer("Computer", 2, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzhhMGM1MDg5N2FjZmM2ZmMzNDUxODUwOGQ5NDBlY2Q1Mjg0NmI3Zjc2NGRmZTI0YTU3ZjZmYmNiNDUzNTE5NCJ9fX0=", "0676e961-2d31-4819-82be-c1b499f00f8f");

    private ItemStack item;
    private String name;
    private String texture;

    Skulls(String name, int id, String texture, String uuid) {
        item = createSkull(texture, name);
        this.texture = texture;
        this.name = name;
    }

    private ItemStack createSkull (String url, String name) {
        ItemStack head = new ItemStack(Material.PLAYER_HEAD, 1, (short) 3);
        if (url.isEmpty()) return head;

        SkullMeta headMeta = (SkullMeta) head.getItemMeta();
        GameProfile gameProfile = new GameProfile(UUID.randomUUID(), null);

        gameProfile.getProperties().put("textures", new Property("textures", url));

        try {
            Field profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, gameProfile);
        } catch (IllegalArgumentException | NoSuchFieldException | SecurityException | IllegalAccessException e) {
            e.printStackTrace();
        }
        head.setItemMeta(headMeta);
        return head;
    }

    public ItemStack getItemStack() {
        return item;
    }

    public String getName() {
        return name;
    }

    public String getTexture() { return texture; }
}
