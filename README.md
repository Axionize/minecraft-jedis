# Jedis for Minecraft

Jedis Redis Client for use as an external library / optional dependency by other plugins or mods.

* This mod doesn't do anything by itself — but it can be used by other plugins/mods that need Jedis Redis Client on the classpath.
* This is a direct repackage of [Jedis Redis Client](https://github.com/redis/jedis) with added mod metadata so it loads as a Bukkit/Spigot/Paper plugin, a Fabric mod, or a Forge / NeoForge mod.
* There are no modifications to the underlying driver.

The jar is universal: one file works on Spigot/Paper from 1.8 to current, Fabric from 1.16.1 to current, Forge from 1.12 to 1.20, and NeoForge from 1.21 onward.

## Why a separate plugin/mod

Server-side database drivers are not bundled with vanilla Minecraft, Fabric, or NeoForge. CraftBukkit and Paper bundle SQLite + MySQL but no other drivers. Plugins and mods that want to use Jedis Redis Client can declare it as a `compileOnly` dependency and probe for its presence at runtime via `Class.forName(...)`. Operators install this jar alongside their other plugins/mods to provide the driver classes on the classpath.

On Paper 1.17+ where each plugin's classloader is isolated, consumers should declare this plugin in their `softdepend` list so the classloader graph links them.

## Released versions

[Modrinth](https://modrinth.com/mod/jedis) — release page, version history, supported game versions.
