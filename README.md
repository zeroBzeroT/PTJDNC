# PTJDNC
Fully configurable playtime + joindate based namecolor plugin for 1.19

In order to get access to any given name color, both the joindate requirement (JD min.) as well as the playtime requirement (PT min.) have to be fulfilled.
The playtime numbers here are measured in hours, whereas the joindate is either measured in days, or a certain date, where the player must have joined before it.

# Example Configuration on 0b0t.org
## Obtainable Namecolors for everyone
| color       | PT min. | JD min.      | example                       | hex code |
|-------------|---------|--------------|-------------------------------|----------|
| gray        | none    | none         | ![](examples/gray.png)        | #AAAAAA  |
| white       | 3       | 3            | ![](examples/white.png)       | #FFFFFF  |
| green       | 6       | 6            | ![](examples/green.png)       | #55FF55  |
| blue        | 12      | 11           | ![](examples/blue.png)        | #5555FF  |
| dark_purple | 24      | 23           | ![](examples/dark_purple.png) | #AA00AA  |
| gold        | 48      | 46           | ![](examples/gold.png)        | #FFAA00  |
| red         | 96      | 91           | ![](examples/red.png)         | #FF5555  |
| yellow      | 192     | 183          | ![](examples/yellow.png)      | #FFFF55  |
| aqua        | 384     | 365          | ![](examples/aqua.png)        | #55FFFF  |
| dark_red    | 768     | 730          | ![](examples/dark_red.png)    | #AA0000  |
| dark_gray   | 1536    | 1460         | ![](examples/dark_gray.png)   | #555555  |

| modifier   | PT min. | JD min.      | example                    |
|------------|---------|--------------|----------------------------|
| bold       | 384     | 365          | ![](examples/bold.png)     |
| italic     | 1536    | 1460         | ![](examples/italic.png)   |

## Donator Namecolors
Donators of 0b0t.org may get special perks for namecolor selection, such as additional namecolors not every other player can have, or an instant access to colors that would require some PT or JD.

| color           | example                              | hex code |
|-----------------|--------------------------------------|----------|
| dark_aqua       | ![](examples/dark_aqua.png)          | #00AAAA  |
| dark_blue       | ![](examples/dark_blue.png)          | #0000AA  |
| dark_green      | ![](examples/dark_green.png)         | #00AA00  |
| light_purple    | ![](examples/light_purple.png)       | #FF55FF  |

## Currently unobtainale Namecolors
On 0b0t.org, these namecolors are not available to anyone except admins as of currently, but this may change in the future.

| color           | example                           | hex code |
|-----------------|-----------------------------------|----------|
| black           | ![](examples/black.png)           | #000000  |

| modifier      | example                         | sample command                  |
|---------------|---------------------------------|---------------------------------|
| strikethrough | ![](examples/strikethrough.png) | /nc white strikethrough         |
| alternating   | ![](examples/alternating.png)   | /nc alternating black white     |
| obfuscated    | ![](examples/obfuscated.png)    | /nc white obfuscated            |
| gradient      | ![](examples/gradient.png)      | /nc gradient dark_red dark_blue |
| flag          | ![](examples/flag.png)          | /nc flag denim white dark_red   |
