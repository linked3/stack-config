# Stackable

Stackable lets you control item stack sizes using a JSON config file.

## What it does
- Generates a config file at `config/stackable.json` on first run.
- Edit the file to set exact max stack sizes for any item or tag.
- Values are clamped to 64.

## How to use
Edit `config/stackable.json` to add entries:

```json
{
  "minecraft:lava_bucket": 16,
  "minecraft:water_bucket": 16,
  "#minecraft:wool": 64
}
```

Remove the file to regenerate defaults.
