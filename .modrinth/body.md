# Jedis (Redis Client)

[Jedis](https://github.com/redis/jedis), the most widely used Java Redis client, repackaged as a Bukkit/Spigot/Paper plugin and a Fabric/Forge/NeoForge mod.

The mod does nothing on its own — it ships Jedis (and its runtime dependencies) onto the server's classpath so other plugins/mods can use it without each bundling their own copy.

## What's in the jar

Jedis plus the four runtime deps it needs:

- `redis.clients:jedis:5.2.0` — the client itself
- `org.apache.commons:commons-pool2` — connection pool (Jedis uses this for `JedisPool`)
- `com.google.code.gson:gson` — JSON serialization
- `org.json:json` — JSON parsing
- `org.slf4j:slf4j-api` — logging facade

slf4j-api is shaded into this jar because Spigot 1.8 doesn't ship it on the classpath. On platforms that already have slf4j (modern Paper, Fabric, NeoForge), the duplicate is harmless — slf4j-api is interface-only, no binding clash.

Loader stubs for Spigot, Forge 1.12, Forge 1.13–1.16, Forge 1.17–1.20, NeoForge 1.21+, and Fabric. No relocation — `redis.clients.jedis.*`, `org.apache.commons.pool2.*`, etc. stay at canonical paths.

## Compatibility

| Loader | MC versions | Notes |
|---|---|---|
| Bukkit / Spigot / Paper / Folia / Purpur | 1.8 → current | drop into `plugins/` |
| Fabric | 1.16.1 → current | needs Fabric Loader 0.14+ |
| Forge | 1.12 → 1.20 | universal jar, no Mixins |
| NeoForge | 1.21 → current | drop into `mods/` |

Java 8+ required (Jedis 5.x baseline).

## Using it from a plugin or mod

```kotlin
compileOnly("redis.clients:jedis:5.2.0")
```

Probe at startup:

```java
try {
    Class.forName("redis.clients.jedis.Jedis");
} catch (ClassNotFoundException e) {
    getLogger().warning("Redis backend disabled — install minecraft-jedis");
    return;
}
try (JedisPool pool = new JedisPool(host, port);
     Jedis jedis = pool.getResource()) {
    jedis.set("hello", "world");
}
```

On Paper 1.17+ add `softdepend: [minecraft-jedis]` to your `plugin.yml`. Fabric and NeoForge don't need anything similar.

For Redis 6+ ACL auth pass `user` and `password`; for older Redis just `password`. TLS is on `JedisClientConfig` if you need it (not wired into the basic `JedisPool` constructor).

## Versioning

The jar version tracks Jedis one-to-one. `5.2.0+2026-04-25` ships Jedis 5.2.0 plus current commons-pool2/gson/json/slf4j-api at the time of build. Auto-bump runs daily — when Jedis releases, the dep tree moves with it.

## License

MIT (Jedis contributors). The repackage adds no functional changes. Full text in [`LICENSE`](https://github.com/Axionize/minecraft-jedis/blob/main/LICENSE).

---

Issues, source: [GitHub](https://github.com/Axionize/minecraft-jedis).
