# Stack Config

Set custom max stack sizes for any item.

## How it works

Edit the `config/stack_config.json` and set stack sizes however you like, by item ID or by tag. The mod hooks into `ItemInstance.getMaxStackSize` to override the vanilla limit at the source.

## Features

- **Per-item and per-tag:** use `minecraft:ender_pearl` to target a single item or `#minecraft:boats` to catch everything in a tag.
- **Sensible defaults:** potions, buckets, minecarts, horse armour, banners, signs, stews, and everything else vanilla limits to 1 or 16 are preconfigured to stack to 16 or 64. First run generates the file with these as a starting point.
- **Bucket behaviour:** placing a stacked solid bucket (powder snow, azalea, etc.) leaves the empty bucket in your hand and adds extras to inventory, so you don't lose items to the usual "bucket returns to hand" logic.
- **Config reload:** edit the file any time; changes apply on the next `getMaxStackSize` call without a restart.

## Config format

```json
{
  "minecraft:ender_pearl": 64,
  "minecraft:saddle": 64,
  "#minecraft:beds": 64
}
```
Prefix with `#` to match a tag.
